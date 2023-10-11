create
    definer = `mysql.sys`@localhost function sys.ps_is_account_enabled(in_host varchar(255), in_user varchar(32)) returns enum ('YES', 'NO')
    comment '
Description
-----------

Determines whether instrumentation of an account is enabled 
within Performance Schema.

Parameters
-----------

in_host VARCHAR(255): 
  The hostname of the account to check.
in_user VARCHAR(32):
  The username of the account to check.

Returns
-----------

ENUM(''YES'', ''NO'', ''PARTIAL'')

Example
-----------

mysql> SELECT sys.ps_is_account_enabled(''localhost'', ''root'');
+------------------------------------------------+
| sys.ps_is_account_enabled(''localhost'', ''root'') |
+------------------------------------------------+
| YES                                            |
+------------------------------------------------+
1 row in set (0.01 sec)
'
    deterministic
    sql security invoker
    reads sql data
BEGIN
    RETURN IF(EXISTS(SELECT 1
                       FROM performance_schema.setup_actors
                      WHERE (`HOST` = '%' OR in_host LIKE `HOST`)
                        AND (`USER` = '%' OR `USER` = in_user)
                        AND (`ENABLED` = 'YES')
                    ),
              'YES', 'NO'
           );
END;

