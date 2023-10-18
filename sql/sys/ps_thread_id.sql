create
    definer = `mysql.sys`@localhost function sys.ps_thread_id(in_connection_id bigint unsigned) returns bigint unsigned
    comment '
Description
-----------

Return the Performance Schema THREAD_ID for the specified connection ID.

Parameters
-----------

in_connection_id (BIGINT UNSIGNED):
  The id of the connection to return the thread id for. If NULL, the current
  connection thread id is returned.

Example
-----------

mysql> SELECT sys.ps_thread_id(79);
+----------------------+
| sys.ps_thread_id(79) |
+----------------------+
|                   98 |
+----------------------+
1 row in set (0.00 sec)

mysql> SELECT sys.ps_thread_id(CONNECTION_ID());
+-----------------------------------+
| sys.ps_thread_id(CONNECTION_ID()) |
+-----------------------------------+
|                                98 |
+-----------------------------------+
1 row in set (0.00 sec)
'
    sql security invoker
    reads sql data
BEGIN
  IF (in_connection_id IS NULL) THEN
    RETURN ps_current_thread_id();
  ELSE
    RETURN ps_thread_id(in_connection_id);
  END IF;
END;

