create
    definer = `mysql.sys`@localhost function sys.extract_schema_from_file_name(path varchar(512)) returns varchar(64)
    comment '
Description
-----------

Takes a raw file path, and attempts to extract the schema name from it.

Useful for when interacting with Performance Schema data 
concerning IO statistics, for example.

Currently relies on the fact that a table data file will be within a 
specified database directory (will not work with partitions or tables
that specify an individual DATA_DIRECTORY).

Parameters
-----------

path (VARCHAR(512)):
  The full file path to a data file to extract the schema name from.

Returns
-----------

VARCHAR(64)

Example
-----------

mysql> SELECT sys.extract_schema_from_file_name(''/var/lib/mysql/employees/employee.ibd'');
+----------------------------------------------------------------------------+
| sys.extract_schema_from_file_name(''/var/lib/mysql/employees/employee.ibd'') |
+----------------------------------------------------------------------------+
| employees                                                                  |
+----------------------------------------------------------------------------+
1 row in set (0.00 sec)
'
    deterministic
    sql security invoker
    no sql
BEGIN
    RETURN LEFT(SUBSTRING_INDEX(SUBSTRING_INDEX(REPLACE(path, '\\', '/'), '/', -2), '/', 1), 64);
END;

