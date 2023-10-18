create
    definer = `mysql.sys`@localhost function sys.ps_is_instrument_default_enabled(in_instrument varchar(128)) returns enum ('YES', 'NO')
    comment '
Description
-----------

Returns whether an instrument is enabled by default in this version of MySQL.

Parameters
-----------

in_instrument VARCHAR(128): 
  The instrument to check.

Returns
-----------

ENUM(''YES'', ''NO'')

Example
-----------

mysql> SELECT sys.ps_is_instrument_default_enabled(''statement/sql/select'');
+--------------------------------------------------------------+
| sys.ps_is_instrument_default_enabled(''statement/sql/select'') |
+--------------------------------------------------------------+
| YES                                                          |
+--------------------------------------------------------------+
1 row in set (0.00 sec)
'
    deterministic
    sql security invoker
    reads sql data
BEGIN
    DECLARE v_enabled ENUM('YES', 'NO');
    IF (in_instrument LIKE 'stage/%') THEN
    BEGIN
      /* Stages are enabled by default if the progress property is set. */
      SET v_enabled = (SELECT
                        IF(find_in_set("progress", PROPERTIES) != 0, 'YES', 'NO')
                        FROM performance_schema.setup_instruments
                        WHERE NAME = in_instrument);
      SET v_enabled = IFNULL(v_enabled, 'NO');
    END;
    ELSE
      SET v_enabled = IF(in_instrument LIKE 'wait/synch/%'
                         OR in_instrument LIKE 'wait/io/socket/%'
                        ,
                         'NO',
                         'YES'
                      );
    END IF;
    RETURN v_enabled;
END;

