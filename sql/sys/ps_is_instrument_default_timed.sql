create
    definer = `mysql.sys`@localhost function sys.ps_is_instrument_default_timed(in_instrument varchar(128)) returns enum ('YES', 'NO')
    comment '
Description
-----------

Returns whether an instrument is timed by default in this version of MySQL.

Parameters
-----------

in_instrument VARCHAR(128): 
  The instrument to check.

Returns
-----------

ENUM(''YES'', ''NO'')

Example
-----------

mysql> SELECT sys.ps_is_instrument_default_timed(''statement/sql/select'');
+------------------------------------------------------------+
| sys.ps_is_instrument_default_timed(''statement/sql/select'') |
+------------------------------------------------------------+
| YES                                                        |
+------------------------------------------------------------+
1 row in set (0.00 sec)
'
    deterministic
    sql security invoker
    reads sql data
BEGIN
    DECLARE v_timed ENUM('YES', 'NO');
    IF (in_instrument LIKE 'stage/%') THEN
    BEGIN
      -- Stages are timed by default if the progress property is set.
      SET v_timed = (SELECT
                      IF(find_in_set("progress", PROPERTIES) != 0, 'YES', 'NO')
                      FROM performance_schema.setup_instruments
                      WHERE NAME = in_instrument);
      SET v_timed = IFNULL(v_timed, 'NO');
    END;
    ELSE
      -- Mutex, rwlock, prlock, sxlock, cond are not timed by default
      -- Memory instruments are never timed.
      SET v_timed = IF(in_instrument LIKE 'wait/synch/%'
                       OR in_instrument LIKE 'memory/%'
                      ,
                       'NO',
                       'YES'
                    );
    END IF;
    RETURN v_timed;
END;

