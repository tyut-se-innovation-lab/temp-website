create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_show_enabled_consumers() comment '
Description
-----------

Shows all currently enabled consumers.

Parameters
-----------

None

Example
-----------

mysql> CALL sys.ps_setup_show_enabled_consumers();

+---------------------------+
| enabled_consumers         |
+---------------------------+
| events_statements_current |
| global_instrumentation    |
| thread_instrumentation    |
| statements_digest         |
+---------------------------+
4 rows in set (0.05 sec)
' deterministic sql security invoker reads sql data
BEGIN
    SELECT name AS enabled_consumers
      FROM performance_schema.setup_consumers
     WHERE enabled = 'YES'
     ORDER BY enabled_consumers;
END;

