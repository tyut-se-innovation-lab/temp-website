create
    definer = `mysql.sys`@localhost function sys.version_major() returns tinyint unsigned comment '
Description
-----------

Returns the major version of MySQL Server.

Returns
-----------

TINYINT UNSIGNED

Example
-----------

mysql> SELECT VERSION(), sys.version_major();
+--------------------------------------+---------------------+
| VERSION()                            | sys.version_major() |
+--------------------------------------+---------------------+
| 5.7.9-enterprise-commercial-advanced | 5                   |
+--------------------------------------+---------------------+
1 row in set (0.00 sec)
' sql security invoker no sql
BEGIN
    RETURN SUBSTRING_INDEX(SUBSTRING_INDEX(VERSION(), '-', 1), '.', 1);
END;

