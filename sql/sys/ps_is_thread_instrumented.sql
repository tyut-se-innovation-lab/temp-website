create
    definer = `mysql.sys`@localhost function sys.ps_is_thread_instrumented(in_connection_id bigint unsigned) returns enum ('YES', 'NO', 'UNKNOWN')
    comment '
Description
-----------

Checks whether the provided connection id is instrumented within Performance Schema.

Parameters
-----------

in_connection_id (BIGINT UNSIGNED):
  The id of the connection to check.

Returns
-----------

ENUM(''YES'', ''NO'', ''UNKNOWN'')

Example
-----------

mysql> SELECT sys.ps_is_thread_instrumented(CONNECTION_ID());
+------------------------------------------------+
| sys.ps_is_thread_instrumented(CONNECTION_ID()) |
+------------------------------------------------+
| YES                                            |
+------------------------------------------------+
'
    sql security invoker
    reads sql data
BEGIN
    DECLARE v_enabled ENUM('YES', 'NO', 'UNKNOWN');
    IF (in_connection_id IS NULL) THEN
        RETURN NULL;
    END IF;
    SELECT INSTRUMENTED INTO v_enabled
      FROM performance_schema.threads 
     WHERE PROCESSLIST_ID = in_connection_id;
    IF (v_enabled IS NULL) THEN
        RETURN 'UNKNOWN';
    ELSE
        RETURN v_enabled;
    END IF;
END;

