create
    definer = `mysql.sys`@localhost function sys.version_patch() returns tinyint unsigned comment '
Description
-----------

Returns the patch release version of MySQL Server.

Returns
-----------

TINYINT UNSIGNED

Example
-----------

mysql> SELECT VERSION(), sys.version_patch();
+--------------------------------------+---------------------+
| VERSION()                            | sys.version_patch() |
+--------------------------------------+---------------------+
| 5.7.9-enterprise-commercial-advanced | 9                   |
+--------------------------------------+---------------------+
1 row in set (0.00 sec)
' sql security invoker no sql
BEGIN
    RETURN SUBSTRING_INDEX(SUBSTRING_INDEX(VERSION(), '-', 1), '.', -1);
END;

