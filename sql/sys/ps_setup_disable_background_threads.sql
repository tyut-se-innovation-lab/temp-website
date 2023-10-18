create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_disable_background_threads() comment '
Description
-----------

Disable all background thread instrumentation within Performance Schema.

Parameters
-----------

None.

Example
-----------

mysql> CALL sys.ps_setup_disable_background_threads();
+--------------------------------+
| summary                        |
+--------------------------------+
| Disabled 18 background threads |
+--------------------------------+
1 row in set (0.00 sec)
' sql security invoker modifies sql data
BEGIN
    UPDATE performance_schema.threads
       SET instrumented = 'NO'
     WHERE type = 'BACKGROUND';
    SELECT CONCAT('Disabled ', @rows := ROW_COUNT(), ' background thread', IF(@rows != 1, 's', '')) AS summary;
END;

