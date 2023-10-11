create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_enable_background_threads() comment '
Description
-----------

Enable all background thread instrumentation within Performance Schema.

Parameters
-----------

None.

Example
-----------

mysql> CALL sys.ps_setup_enable_background_threads();
+-------------------------------+
| summary                       |
+-------------------------------+
| Enabled 18 background threads |
+-------------------------------+
1 row in set (0.00 sec)
' sql security invoker modifies sql data
BEGIN
    UPDATE performance_schema.threads
       SET instrumented = 'YES'
     WHERE type = 'BACKGROUND';
    SELECT CONCAT('Enabled ', @rows := ROW_COUNT(), ' background thread', IF(@rows != 1, 's', '')) AS summary;
END;

