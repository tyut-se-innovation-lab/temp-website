create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_enable_thread(IN in_connection_id bigint) comment '
Description
-----------

Enable the given connection/thread in Performance Schema.

Parameters
-----------

in_connection_id (BIGINT):
  The connection ID (PROCESSLIST_ID from performance_schema.threads
  or the ID shown within SHOW PROCESSLIST)

Example
-----------

mysql> CALL sys.ps_setup_enable_thread(3);
+------------------+
| summary          |
+------------------+
| Enabled 1 thread |
+------------------+
1 row in set (0.01 sec)

To enable the current connection:

mysql> CALL sys.ps_setup_enable_thread(CONNECTION_ID());
+------------------+
| summary          |
+------------------+
| Enabled 1 thread |
+------------------+
1 row in set (0.00 sec)
' sql security invoker modifies sql data
BEGIN
    UPDATE performance_schema.threads
       SET instrumented = 'YES'
     WHERE processlist_id = in_connection_id;
    SELECT CONCAT('Enabled ', @rows := ROW_COUNT(), ' thread', IF(@rows != 1, 's', '')) AS summary;
END;

