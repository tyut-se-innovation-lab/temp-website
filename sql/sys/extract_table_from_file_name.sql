create
    definer = `mysql.sys`@localhost function sys.extract_table_from_file_name(path varchar(512)) returns varchar(64)
    comment '
Description
-----------

Takes a raw file path, and extracts the table name from it.

Useful for when interacting with Performance Schema data 
concerning IO statistics, for example.

Parameters
-----------

path (VARCHAR(512)):
  The full file path to a data file to extract the table name from.

Returns
-----------

VARCHAR(64)

Example
-----------

mysql> SELECT sys.extract_table_from_file_name(''/var/lib/mysql/employees/employee.ibd'');
+---------------------------------------------------------------------------+
| sys.extract_table_from_file_name(''/var/lib/mysql/employees/employee.ibd'') |
+---------------------------------------------------------------------------+
| employee                                                                  |
+---------------------------------------------------------------------------+
1 row in set (0.02 sec)
'
    deterministic
    sql security invoker
    no sql
BEGIN
    RETURN LEFT(SUBSTRING_INDEX(REPLACE(SUBSTRING_INDEX(REPLACE(path, '\\', '/'), '/', -1), '@0024', '$'), '.', 1), 64);
END;

