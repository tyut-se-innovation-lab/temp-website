create
    definer = `mysql.sys`@localhost function sys.ps_thread_account(in_thread_id bigint unsigned) returns text comment '
Description
-----------

Return the user@host account for the given Performance Schema thread id.

Parameters
-----------

in_thread_id (BIGINT UNSIGNED):
  The id of the thread to return the account for.

Example
-----------

mysql> select thread_id, processlist_user, processlist_host from performance_schema.threads where type = ''foreground'';
+-----------+------------------+------------------+
| thread_id | processlist_user | processlist_host |
+-----------+------------------+------------------+
|        23 | NULL             | NULL             |
|        30 | root             | localhost        |
|        31 | msandbox         | localhost        |
|        32 | msandbox         | localhost        |
+-----------+------------------+------------------+
4 rows in set (0.00 sec)

mysql> select sys.ps_thread_account(31);
+---------------------------+
| sys.ps_thread_account(31) |
+---------------------------+
| msandbox@localhost        |
+---------------------------+
1 row in set (0.00 sec)
' sql security invoker reads sql data
BEGIN
    RETURN (SELECT IF(
                      type = 'FOREGROUND',
                      CONCAT(processlist_user, '@', processlist_host),
                      type
                     ) AS account
              FROM `performance_schema`.`threads`
             WHERE thread_id = in_thread_id);
END;

