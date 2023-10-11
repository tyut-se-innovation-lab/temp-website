create
    definer = `mysql.sys`@localhost function sys.format_bytes(bytes text) returns text comment '
Description
-----------

Takes a raw bytes value, and converts it to a human readable format.

Parameters
-----------

bytes (TEXT):
  A raw bytes value.

Returns
-----------

TEXT

Example
-----------

mysql> SELECT sys.format_bytes(2348723492723746) AS size;
+----------+
| size     |
+----------+
| 2.09 PiB |
+----------+
1 row in set (0.00 sec)

mysql> SELECT sys.format_bytes(2348723492723) AS size;
+----------+
| size     |
+----------+
| 2.14 TiB |
+----------+
1 row in set (0.00 sec)

mysql> SELECT sys.format_bytes(23487234) AS size;
+-----------+
| size      |
+-----------+
| 22.40 MiB |
+-----------+
1 row in set (0.00 sec)
' deterministic sql security invoker no sql
BEGIN
  IF (bytes IS NULL) THEN
    RETURN NULL;
  ELSE
    RETURN format_bytes(bytes);
  END IF;
END;

