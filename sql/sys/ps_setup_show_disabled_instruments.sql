create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_show_disabled_instruments() comment '
Description
-----------

Shows all currently disabled instruments.

Parameters
-----------

None

Example
-----------

mysql> CALL sys.ps_setup_show_disabled_instruments();
' deterministic sql security invoker reads sql data
BEGIN
    SELECT name AS disabled_instruments, timed
      FROM performance_schema.setup_instruments
     WHERE enabled = 'NO'
     ORDER BY disabled_instruments;
END;

