/**********************************************************************
Copyright (c) 2005 Andy Jefferson and others. All rights reserved. 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
 

Contributors:
    ...
**********************************************************************/
package org.datanucleus.store.rdbms.scostore;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.mapping.java.InterfaceMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.DatastoreIdMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.scostore.Store;

/**
 * Base class for all mapped container stores (collections, maps, arrays).
 * Provides a series of helper methods for handling the store process.
 */
public abstract class BaseContainerStore implements Store
{
    /** Manager for the store. */
    protected RDBMSStoreManager storeMgr;

    /** Datastore adapter in use by this store. */
    protected DatastoreAdapter dba;

    /** Mapping to the owner of the container. */
    protected JavaTypeMapping ownerMapping;

    /** MetaData for the field/property in the owner with this container. */
    protected AbstractMemberMetaData ownerMemberMetaData;

    /** Type of relation (1-N uni, 1-N bi, M-N). */
    protected RelationType relationType;

    /** Whether the container allows null elements/values. */
    protected boolean allowNulls = false;

    protected ClassLoaderResolver clr;

    /**
     * Constructor.
     * @param storeMgr Manager for the datastore being used
     * @param clr ClassLoader resolver
     */
    protected BaseContainerStore(RDBMSStoreManager storeMgr, ClassLoaderResolver clr)
    {
        this.storeMgr = storeMgr;
        this.dba = this.storeMgr.getDatastoreAdapter();
        this.clr = clr;
    }

    /**
     * Method to set the owner for this backing store.
     * Sets the metadata of the owner field/property, as well as whether nulls are allowed, and the relation type.
     * @param mmd MetaData for the member owning this backing store.
     */
    protected void setOwner(AbstractMemberMetaData mmd)
    {
        this.ownerMemberMetaData = mmd;
        if (Boolean.TRUE.equals(ownerMemberMetaData.getContainer().allowNulls()))
        {
            // User has requested allowing nulls in this field/property
            allowNulls = true;
        }
        this.relationType = ownerMemberMetaData.getRelationType(clr);
    }

    public RDBMSStoreManager getStoreManager()
    {
        return storeMgr;
    }

    public JavaTypeMapping getOwnerMapping()
    {
        return ownerMapping;
    }

    public RelationType getRelationType()
    {
        return relationType;
    }

    public AbstractMemberMetaData getOwnerMemberMetaData()
    {
        return ownerMemberMetaData;
    }

    public DatastoreAdapter getDatastoreAdapter()
    {
        return dba;
    }

    /**
     * Check if the mapping correspond to a non pc object or embedded field
     * @param mapping the mapping
     * @return true if the field is embedded into one column
     */
    protected boolean isEmbeddedMapping(JavaTypeMapping mapping)
    {
        return !InterfaceMapping.class.isAssignableFrom(mapping.getClass()) &&
               !DatastoreIdMapping.class.isAssignableFrom(mapping.getClass()) &&
               !PersistableMapping.class.isAssignableFrom(mapping.getClass());
    }

    /**
     * Method to return the ObjectProvider for an embedded PC object (element, key, value).
     * It creates one if the element is not currently managed.
     * @param op ObjectProvider of the owner
     * @param obj The embedded PC object
     * @param ownerMmd The meta data for the owner field
     * @param pcType Object type for the embedded object (see ObjectProvider EMBEDDED_PC etc)
     * @return The ObjectProvider
     */
    public ObjectProvider getObjectProviderForEmbeddedPCObject(ObjectProvider op, Object obj, AbstractMemberMetaData ownerMmd, short pcType)
    {
        ExecutionContext ec = op.getExecutionContext();
        ObjectProvider objOP = ec.findObjectProvider(obj);
        if (objOP == null)
        {
            objOP = ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, obj, false, op, ownerMmd.getAbsoluteFieldNumber());
        }
        objOP.setPcObjectType(pcType);
        return objOP;
    }

    /**
     * Convenience method to return if the datastore supports batching and the user wants batching.
     * @return If batching of statements is permissible
     */
    protected boolean allowsBatching()
    {
        return storeMgr.allowsBatching();
    }

    /**
     * Convenience method to find the element information relating to the element type.
     * Used specifically for the "element-type" of a collection/array to find the elements which have table information.
     * @param componentType Type of the component
     * @param componentCmd Metadata for the root component class TODO Get rid of this
     * @return Element information relating to the element type
     */
    protected ComponentInfo[] getComponentInformationForClass(String componentType, AbstractClassMetaData componentCmd)
    {
        ComponentInfo[] info = null;

        DatastoreClass rootTbl;
        String[] clsNames;
        if (clr.classForName(componentType).isInterface())
        {
            // Collection<interface>, so find implementations of the interface
            clsNames = storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(componentType, clr);
            rootTbl = null;
        }
        else
        {
            clsNames = new String[] {componentType};
            rootTbl = storeMgr.getDatastoreClass(componentType, clr);
        }

        if (rootTbl == null)
        {
            if (clr.classForName(componentType).isInterface())
            {
                info = new ComponentInfo[clsNames.length];
                for (int i=0;i<clsNames.length;i++)
                {
                    AbstractClassMetaData implCmd = storeMgr.getMetaDataManager().getMetaDataForClass(clsNames[i], clr);
                    DatastoreClass table = storeMgr.getDatastoreClass(clsNames[i], clr);
                    info[i] = new ComponentInfo(implCmd, table);
                }
            }
            else
            {
                AbstractClassMetaData[] subclassCmds = storeMgr.getClassesManagingTableForClass(componentCmd, clr);
                info = new ComponentInfo[subclassCmds.length];
                for (int i=0;i<subclassCmds.length;i++)
                {
                    DatastoreClass table = storeMgr.getDatastoreClass(subclassCmds[i].getFullClassName(), clr);
                    info[i] = new ComponentInfo(subclassCmds[i], table);
                }
            }
        }
        else
        {
            info = new ComponentInfo[clsNames.length];
            for (int i=0; i<clsNames.length; i++)
            {
                AbstractClassMetaData cmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(clsNames[i], clr);
                DatastoreClass table = storeMgr.getDatastoreClass(cmd.getFullClassName(), clr);
                info[i] = new ComponentInfo(cmd, table);
            }
        }

        return info;
    }
}