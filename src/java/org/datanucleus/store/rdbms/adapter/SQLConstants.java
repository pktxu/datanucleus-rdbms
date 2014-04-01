/**********************************************************************
Copyright (c) 2002 Mike Martin (TJDO) and others. All rights reserved.
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
2003 Andy Jefferson - moved to RDBMS
    ...
**********************************************************************/
package org.datanucleus.store.rdbms.adapter;

/**
 * Contains various constants from the SQL/92, SQL/99 and SQL 2003 standard.
 *
 * <h3>SQL/92, SQL/99 and SQL 2003 Key Words</h3>
 * A key word is a word that has one or more meanings in the SQLlanguage itself.
 * Some key words are reserved words, meaning they cannot be used as a regular identifier.
 * The remaining key words are non-reserved.
 *
 * @see org.datanucleus.store.rdbms.adapter.BaseDatastoreAdapter
 */
public interface SQLConstants
{
    /**
     * The maximum length of a SQL/92 identifier in characters.
     */
    public static final int MAX_IDENTIFIER_LENGTH = 128;

    /**
     * A string containing the list of SQL/92 <dfn>reserved words</dfn>,
     * separated by commas.
     */
    public static final String SQL92_RESERVED_WORDS =
        "ABSOLUTE,ACTION,ADD,ALL,ALLOCATE,ALTER,AND," +
        "ANY,ARE,AS,ASC," +
        "ASSERTION,AT,AUTHORIZATION,AVG," +
        "BEGIN,BETWEEN,BIT,BIT_LENGTH,BOTH,BY," +
        "CASCADE,CASCADED,CASE,CAST,CATALOG,CHAR,CHARACTER,CHAR_LENGTH," +
        "CHARACTER_LENGTH,CHECK,CLOSE,COALESCE,COLLATE,COLLATION," +
        "COLUMN,COMMIT,CONNECT,CONNECTION,CONSTRAINT," +
        "CONSTRAINTS,CONTINUE," +
        "CONVERT,CORRESPONDING,COUNT,CREATE,CROSS,CURRENT," +
        "CURRENT_DATE,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_USER,CURSOR," +
        "DATE,DAY,DEALLOCATE,DEC,DECIMAL,DECLARE,DEFAULT,DEFERRABLE," +
        "DEFERRED,DELETE,DESC,DESCRIBE,DESCRIPTOR,DIAGNOSTICS," +
        "DISCONNECT,DISTINCT,DOMAIN,DOUBLE,DROP," +
        "ELSE,END,END-EXEC,ESCAPE,EXCEPT,EXCEPTION," +
        "EXEC,EXECUTE,EXISTS," +
        "EXTERNAL,EXTRACT," +
        "FALSE,FETCH,FIRST,FLOAT,FOR,FOREIGN,FOUND,FROM,FULL," +
        "GET,GLOBAL,GO,GOTO,GRANT,GROUP," +
        "HAVING,HOUR," +
        "IDENTITY,IMMEDIATE,IN,INDICATOR,INITIALLY,INNER,INPUT," +
        "INSENSITIVE,INSERT,INT,INTEGER,INTERSECT,INTERVAL,INTO,IS," +
        "ISOLATION," +
        "JOIN," +
        "KEY," +
        "LANGUAGE,LAST,LEADING,LEFT,LEVEL,LIKE,LOCAL,LOWER," +
        "MATCH,MAX,MIN,MINUTE,MODULE,MONTH," +
        "NAMES,NATIONAL,NATURAL,NCHAR,NEXT,NO,NOT,NULL," +
        "NULLIF,NUMERIC," +
        "OCTET_LENGTH,OF,ON,ONLY,OPEN,OPTION,OR," +
        "ORDER,OUTER," +
        "OUTPUT,OVERLAPS," +
        "PAD,PARTIAL,POSITION,PRECISION,PREPARE,PRESERVE,PRIMARY," +
        "PRIOR,PRIVILEGES,PROCEDURE,PUBLIC," +
        "READ,REAL,REFERENCES,RELATIVE,RESTRICT,REVOKE,RIGHT," +
        "ROLLBACK,ROWS," +
        "SCHEMA,SCROLL,SECOND,SECTION,SELECT,SESSION,SESSION_USER,SET," +
        "SIZE,SMALLINT,SOME,SPACE,SQL,SQLCODE,SQLERROR,SQLSTATE," +
        "SUBSTRING,SUM,SYSTEM_USER," +
        "TABLE,TEMPORARY,THEN,TIME,TIMESTAMP,TIMEZONE_HOUR,TIMEZONE_MINUTE," +
        "TO,TRAILING,TRANSACTION,TRANSLATE,TRANSLATION,TRIM,TRUE," +
        "UNION,UNIQUE,UNKNOWN,UPDATE,UPPER,USAGE,USER,USING," +
        "VALUE,VALUES,VARCHAR,VARYING,VIEW," +
        "WHEN,WHENEVER,WHERE,WITH,WORK,WRITE," +
        "YEAR," +
        "ZONE";
    
    /**
     * A string containing the list of SQL/99 <dfn>reserved words</dfn>,
     * separated by commas.
     */
    public static final String SQL99_RESERVED_WORDS =
        "ABSOLUTE,ACTION,ADD,AFTER,ALL,ALLOCATE,ALTER,AND," +
        "ANY,ARE,ARRAY,AS,ASC,ASENSITIVE," +
        "ASSERTION,ASYMMETRIC,AT,ATOMIC,AUTHORIZATION," +
        "BEFORE,BEGIN,BETWEEN,BINARY,BIT,BLOB,BOOLEAN," +
        "BOTH,BREADTH,BY," +
        "CALL,CALLED,CASCADE,CASCADED,CASE,CAST,CATALOG," +
        "CHAR,CHARACTER,CHECK,CLOB,CLOSE,COLLATE," +
        "COLLATION,COLUMN,COMMIT,CONDITION,CONNECT," +
        "CONNECTION,CONSTRAINT,CONSTRAINTS,CONSTRUCTOR," +
        "CONTINUE,CORRESPONDING,CREATE,CROSS,CUBE,CURRENT," +
        "CURRENT_DATE,CURRENT_DEFAULT_TRANSFORM_GROUP," +
        "CURRENT_PATH,CURRENT_ROLE,CURRENT_TIME,CURRENT_TIMESTAMP," +
        "CURRENT_TRANSFORM_GROUP_FOR_TYPE,CURRENT_USER,CURSOR,CYCLE," +
        "DATA,DATE,DAY,DEALLOCATE,DEC,DECIMAL,DECLARE,DEFAULT," +
        "DEFERRABLE,DEFERRED,DELETE,DEPTH,DEREF,DESC,DESCRIBE," +
        "DESCRIPTOR,DETERMINISTIC,DIAGNOSTICS,DISCONNECT," +
        "DISTINCT,DO,DOMAIN,DOUBLE,DROP,DYNAMIC," +
        "EACH,ELSE,ELSEIF,END,EQUALS,ESCAPE,EXCEPT,EXCEPTION," +
        "EXEC,EXECUTE,EXISTS,EXIT,EXTERNAL," +
        "FALSE,FETCH,FILTER,FIRST,FLOAT,FOR,FOREIGN," +
        "FOUND,FREE,FROM,FULL,FUNCTION," +
        "GENERAL,GET,GLOBAL,GO,GOTO,GRANT,GROUP,GROUPING," +
        "HANDLER,HAVING,HOLD,HOUR,I" +
        "DENTITY,IF,IMMEDIATE,IN,INDICATOR,INITIALLY," +
        "INNER,INOUT,INPUT,INSENSITIVE,INSERT,INT,INTEGER," +
        "INTERSECT,INTERVAL,INTO,IS,ISOLATION,ITERATE," +
        "JOIN," +
        "KEY," +
        "LANGUAGE,LARGE,LAST,LATERAL,LEADING,LEAVE,LEFT," +
        "LEVEL,LIKE,LOCAL,LOCALTIME,LOCALTIMESTAMP,LOCATOR,LOOP," +
        "MAP,MATCH,METHOD,MINUTE,MODIFIES,MODULE,MONTH," +
        "NAMES,NATIONAL,NATURAL,NCHAR,NCLOB,NEW,NEXT," +
        "NO,NONE,NOT,NULL,NUMERIC," +
        "OBJECT,OF,OLD,ON,ONLY,OPEN,OPTION,OR,ORDER," +
        "ORDINALITY,OUT,OUTER,OUTPUT,OVER,OVERLAPS," +
        "PAD,PARAMETER,PARTIAL,PARTITION,PATH,PRECISION," +
        "PREPARE,PRESERVE,PRIMARY,PRIOR,PRIVILEGES," +
        "PROCEDURE,PUBLIC," +
        "RANGE,READ,READS,REAL,RECURSIVE,REF,REFERENCES," +
        "REFERENCING,RELATIVE,RELEASE,REPEAT,RESIGNAL," +
        "RESTRICT,RESULT,RETURN,RETURNS,REVOKE,RIGHT," +
        "ROLE,ROLLBACK,ROLLUP,ROUTINE,ROW,ROWS," +
        "SAVEPOINT,SCHEMA,SCOPE,SCROLL,SEARCH,SECOND," +
        "SECTION,SELECT,SENSITIVE,SESSION,SESSION_USER," +
        "SET,SETS,SIGNAL,SIMILAR,SIZE,SMALLINT,SOME," +
        "SPACE,SPECIFIC,SPECIFICTYPE,SQL,SQLEXCEPTION," +
        "SQLSTATE,SQLWARNING,START,STATE,STATIC,SYMMETRIC," +
        "SYSTEM,SYSTEM_USER," +
        "TABLE,TEMPORARY,THEN,TIME," +
        "TIMESTAMP,TIMEZONE_HOUR,TIMEZONE_MINUTE,TO,TRAILING," +
        "TRANSACTION,TRANSLATION,TREAT,TRIGGER,TRUE," +
        "UNDER,UNDO,UNION,UNIQUE,UNKNOWN,UNNEST," +
        "UNTIL,UPDATE,USAGE,USER,USING," +
        "VALUE,VALUES,VARCHAR,VARYING,VIEW," +
        "WHEN,WHENEVER,WHERE,WHILE,WINDOW,WITH,WITHIN," +
        "WITHOUT,WORK,WRITE," +
        "YEAR," +
        "ZONE";

    /**
     * A string containing the list of SQL/2003 <dfn>reserved words</dfn>,
     * separated by commas.
     */
    public static final String SQL2003_RESERVED_WORDS =
        "ADD,ALL,ALLOCATE,ALTER,AND,ANY,ARE,ARRAY," +
        "AS,ASENSITIVE,ASYMMETRIC,AT,ATOMIC,AUTHORIZATION," +
        "BEGIN,BETWEEN,BIGINT,BINARY,BLOB,BOOLEAN,BOTH,BY," +
        "CALL,CALLED,CASCADED,CASE,CAST,CHAR,CHARACTER," +
        "CHECK,CLOB,CLOSE,COLLATE,COLUMN,COMMIT,CONDITION," +
        "CONNECT,CONSTRAINT,CONTINUE,CORRESPONDING,CREATE," +
        "CROSS,CUBE,CURRENT,CURRENT_DATE," +
        "CURRENT_DEFAULT_TRANSFORM_GROUP,CURRENT_PATH," +
        "CURRENT_ROLE,CURRENT_TIME,CURRENT_TIMESTAMP," +
        "CURRENT_TRANSFORM_GROUP_FOR_TYPE,CURRENT_USER," +
        "CURSOR,CYCLE," +
        "DATE,DAY,DEALLOCATE,DEC,DECIMAL,DECLARE,DEFAULT," +
        "DELETE,DEREF,DESCRIBE,DETERMINISTIC,DISCONNECT," +
        "DISTINCT,DO,DOUBLE,DROP,DYNAMIC," +
        "EACH,ELEMENT,ELSE,ELSEIF,END,ESCAPE,EXCEPT,EXEC," +
        "EXECUTE,EXISTS,EXIT,EXTERNAL," +
        "FALSE,FETCH,FILTER,FLOAT,FOR,FOREIGN,FREE,FROM," +
        "FULL,FUNCTION," +
        "GET,GLOBAL,GRANT,GROUP,GROUPING," +
        "HANDLER,HAVING,HOLD,HOUR," +
        "IDENTITY,IF,IMMEDIATE,IN,INDICATOR,INNER,INOUT," +
        "INPUT,INSENSITIVE,INSERT,INT,INTEGER,INTERSECT," +
        "INTERVAL,INTO,IS,ITERATE," +
        "JOIN," +
        "LANGUAGE,LARGE,LATERAL,LEADING,LEAVE,LEFT,LIKE," +
        "LOCAL,LOCALTIME,LOCALTIMESTAMP,LOOP," +
        "MATCH,MEMBER,MERGE,METHOD,MINUTE,MODIFIES,MODULE," +
        "MONTH,MULTISET," +
        "NATIONAL,NATURAL,NCHAR,NCLOB,NEW,NO,NONE,NOT,NULL," +
        "NUMERIC," +
        "OF,OLD,ON,ONLY,OPEN,OR,ORDER,OUT,OUTER,OUTPUT,OVER," +
        "OVERLAPS," +
        "PARAMETER,PARTITION,PRECISION,PREPARE,PRIMARY," +
        "PROCEDURE," +
        "RANGE,READS,REAL,RECURSIVE,REF,REFERENCES,REFERENCING," +
        "RELEASE,REPEAT,RESIGNAL,RESULT,RETURN,RETURNS,REVOKE," +
        "RIGHT,ROLLBACK,ROLLUP,ROW,ROWS," +
        "SAVEPOINT,SCOPE,SCROLL,SEARCH,SECOND,SELECT,SENSITIVE," +
        "SESSION_USER,SET,SIGNAL,SIMILAR,SMALLINT,SOME,SPECIFIC," +
        "SPECIFICTYPE,SQL,SQLEXCEPTION,SQLSTATE,SQLWARNING,START," +
        "STATIC,SUBMULTISET,SYMMETRIC,SYSTEM,SYSTEM_USER," +
        "TABLE,TABLESAMPLE,THEN,TIME,TIMESTAMP,TIMEZONE_HOUR," +
        "TIMEZONE_MINUTE,TO,TRAILING,TRANSLATION,TREAT,TRIGGER,TRUE," +
        "UNDO,UNION,UNIQUE,UNKNOWN,UNNEST,UNTIL,UPDATE,USER,USING," +
        "VALUE,VALUES,VARCHAR,VARYING," +
        "WHEN,WHENEVER,WHERE,WHILE,WINDOW,WITH,WITHIN,WITHOUT," +
        "YEAR";

    /**
     * A string containing the list of SQL/92 <dfn>non-reserved words</dfn>,
     * separated by commas.
     */
    public static final String NONRESERVED_WORDS =
        "ADA," +
        "C,CATALOG_NAME,CHARACTER_SET_CATALOG,CHARACTER_SET_NAME," +
        "CHARACTER_SET_SCHEMA,CLASS_ORIGIN,COBOL,COLLATION_CATALOG," +
        "COLLATION_NAME,COLLATION_SCHEMA,COLUMN_NAME,COMMAND_FUNCTION,COMMITTED," +
        "CONDITION_NUMBER,CONNECTION_NAME,CONSTRAINT_CATALOG,CONSTRAINT_NAME," +
        "CONSTRAINT_SCHEMA,CURSOR_NAME," +
        "DATA,DATETIME_INTERVAL_CODE,DATETIME_INTERVAL_PRECISION,DYNAMIC_FUNCTION," +
        "FORTRAN," +
        "LENGTH," +
        "MESSAGE_LENGTH,MESSAGE_OCTET_LENGTH,MESSAGE_TEXT,MORE,MUMPS," +
        "NAME,NULLABLE,NUMBER," +
        "PASCAL,PLI," +
        "REPEATABLE,RETURNED_LENGTH,RETURNED_OCTET_LENGTH,RETURNED_SQLSTATE," +
        "ROW_COUNT," +
        "SCALE,SCHEMA_NAME,SERIALIZABLE,SERVER_NAME,SUBCLASS_ORIGIN," +
        "TABLE_NAME,TYPE," +
        "UNCOMMITTED,UNNAMED";
}
