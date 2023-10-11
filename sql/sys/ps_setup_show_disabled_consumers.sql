create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_show_disabled_consumers() comment '
Description
-----------

Shows all currently disabled consumers.

Parameters
-----------

None

Example
-----------

mysql> CALL sys.ps_setup_show_disabled_consumers();

+---------------------------+
| disabled_consumers        |
+---------------------------+
| events_statements_current |
| global_instrumentation    |
| thread_instrumentation    |
| statements_digest         |
+---------------------------+
4 rows in set (0.05 sec)
' deterministic sql security invoker reads sql data
BEGIN
    SELECT name AS disabled_consumers
      FROM performance_schema.setup_consumers
     WHERE enabled = 'NO'
     ORDER BY disabled_consumers;
END;

