create definer = `mysql.sys`@localhost view sys.innodb_lock_waits as
select `r`.`trx_wait_started`                                                                                    AS `wait_started`,
       timediff(now(), `r`.`trx_wait_started`)                                                                   AS `wait_age`,
       timestampdiff(SECOND, `r`.`trx_wait_started`, now())                                                      AS `wait_age_secs`,
       concat(`sys`.`quote_identifier`(`rl`.`OBJECT_SCHEMA`), '.',
              `sys`.`quote_identifier`(`rl`.`OBJECT_NAME`))                                                      AS `locked_table`,
       `rl`.`OBJECT_SCHEMA`                                                                                      AS `locked_table_schema`,
       `rl`.`OBJECT_NAME`                                                                                        AS `locked_table_name`,
       `rl`.`PARTITION_NAME`                                                                                     AS `locked_table_partition`,
       `rl`.`SUBPARTITION_NAME`                                                                                  AS `locked_table_subpartition`,
       `rl`.`INDEX_NAME`                                                                                         AS `locked_index`,
       `rl`.`LOCK_TYPE`                                                                                          AS `locked_type`,
       `r`.`trx_id`                                                                                              AS `waiting_trx_id`,
       `r`.`trx_started`                                                                                         AS `waiting_trx_started`,
       timediff(now(), `r`.`trx_started`)                                                                        AS `waiting_trx_age`,
       `r`.`trx_rows_locked`                                                                                     AS `waiting_trx_rows_locked`,
       `r`.`trx_rows_modified`                                                                                   AS `waiting_trx_rows_modified`,
       `r`.`trx_mysql_thread_id`                                                                                 AS `waiting_pid`,
       `sys`.`format_statement`(`r`.`trx_query`)                                                                 AS `waiting_query`,
       `rl`.`ENGINE_LOCK_ID`                                                                                     AS `waiting_lock_id`,
       `rl`.`LOCK_MODE`                                                                                          AS `waiting_lock_mode`,
       `b`.`trx_id`                                                                                              AS `blocking_trx_id`,
       `b`.`trx_mysql_thread_id`                                                                                 AS `blocking_pid`,
       `sys`.`format_statement`(`b`.`trx_query`)                                                                 AS `blocking_query`,
       `bl`.`ENGINE_LOCK_ID`                                                                                     AS `blocking_lock_id`,
       `bl`.`LOCK_MODE`                                                                                          AS `blocking_lock_mode`,
       `b`.`trx_started`                                                                                         AS `blocking_trx_started`,
       timediff(now(), `b`.`trx_started`)                                                                        AS `blocking_trx_age`,
       `b`.`trx_rows_locked`                                                                                     AS `blocking_trx_rows_locked`,
       `b`.`trx_rows_modified`                                                                                   AS `blocking_trx_rows_modified`,
       concat('KILL QUERY ', `b`.`trx_mysql_thread_id`)                                                          AS `sql_kill_blocking_query`,
       concat('KILL ', `b`.`trx_mysql_thread_id`)                                                                AS `sql_kill_blocking_connection`
from ((((`performance_schema`.`data_lock_waits` `w` join `information_schema`.`INNODB_TRX` `b` on ((`b`.`trx_id` =
                                                                                                    cast(`w`.`BLOCKING_ENGINE_TRANSACTION_ID` as char charset utf8mb4)))) join `information_schema`.`INNODB_TRX` `r`
        on ((`r`.`trx_id` =
             cast(`w`.`REQUESTING_ENGINE_TRANSACTION_ID` as char charset utf8mb4)))) join `performance_schema`.`data_locks` `bl`
       on ((`bl`.`ENGINE_LOCK_ID` = `w`.`BLOCKING_ENGINE_LOCK_ID`))) join `performance_schema`.`data_locks` `rl`
      on ((`rl`.`ENGINE_LOCK_ID` = `w`.`REQUESTING_ENGINE_LOCK_ID`)))
order by `r`.`trx_wait_started`;

