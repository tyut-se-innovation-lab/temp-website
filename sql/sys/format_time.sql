create
    definer = `mysql.sys`@localhost function sys.format_time(picoseconds text) returns text comment '
Description
-----------

Takes a raw picoseconds value, and converts it to a human readable form.

Picoseconds are the precision that all latency values are printed in
within Performance Schema, however are not user friendly when wanting
to scan output from the command line.

Parameters
-----------

picoseconds (TEXT):
  The raw picoseconds value to convert.

Returns
-----------

TEXT CHARSET UTF8MB4

Example
-----------

mysql> select format_time(342342342342345);
+------------------------------+
| format_time(342342342342345) |
+------------------------------+
| 00:05:42                     |
+------------------------------+
1 row in set (0.00 sec)

mysql> select format_time(342342342);
+------------------------+
| format_time(342342342) |
+------------------------+
| 342.34 us              |
+------------------------+
1 row in set (0.00 sec)

mysql> select format_time(34234);
+--------------------+
| format_time(34234) |
+--------------------+
| 34.23 ns           |
+--------------------+
1 row in set (0.00 sec)
' deterministic sql security invoker no sql
BEGIN
  IF picoseconds IS NULL THEN RETURN NULL;
  ELSEIF picoseconds >= 604800000000000000 THEN RETURN CONCAT(ROUND(picoseconds / 604800000000000000, 2), ' w');
  ELSEIF picoseconds >= 86400000000000000 THEN RETURN CONCAT(ROUND(picoseconds / 86400000000000000, 2), ' d');
  ELSEIF picoseconds >= 3600000000000000 THEN RETURN CONCAT(ROUND(picoseconds / 3600000000000000, 2), ' h');
  ELSEIF picoseconds >= 60000000000000 THEN RETURN CONCAT(ROUND(picoseconds / 60000000000000, 2), ' m');
  ELSEIF picoseconds >= 1000000000000 THEN RETURN CONCAT(ROUND(picoseconds / 1000000000000, 2), ' s');
  ELSEIF picoseconds >= 1000000000 THEN RETURN CONCAT(ROUND(picoseconds / 1000000000, 2), ' ms');
  ELSEIF picoseconds >= 1000000 THEN RETURN CONCAT(ROUND(picoseconds / 1000000, 2), ' us');
  ELSEIF picoseconds >= 1000 THEN RETURN CONCAT(ROUND(picoseconds / 1000, 2), ' ns');
  ELSE RETURN CONCAT(picoseconds, ' ps');
  END IF;
END;

