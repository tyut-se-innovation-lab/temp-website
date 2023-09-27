create definer = `mysql.sys`@localhost view sys.x$processlist as
select `pps`.`THREAD_ID`                                                                                              AS `thd_id`,
       `pps`.`PROCESSLIST_ID`                                                                                         AS `conn_id`,
       if((`pps`.`NAME` in ('thread/sql/one_connection', 'thread/thread_pool/tp_one_connection')),
          concat(`pps`.`PROCESSLIST_USER`, '@', convert(`pps`.`PROCESSLIST_HOST` using utf8mb4)),
          replace(`pps`.`NAME`, 'thread/', ''))                                                                       AS `user`,
       `pps`.`PROCESSLIST_DB`                                                                                         AS `db`,
       `pps`.`PROCESSLIST_COMMAND`                                                                                    AS `command`,
       `pps`.`PROCESSLIST_STATE`                                                                                      AS `state`,
       `pps`.`PROCESSLIST_TIME`                                                                                       AS `time`,
       `pps`.`PROCESSLIST_INFO`                                                                                       AS `current_statement`,
       `pps`.`EXECUTION_ENGINE`                                                                                       AS `execution_engine`,
       if((`esc`.`END_EVENT_ID` is null), `esc`.`TIMER_WAIT`, NULL)                                                   AS `statement_latency`,
       if((`esc`.`END_EVENT_ID` is null), round((100 * (`estc`.`WORK_COMPLETED` / `estc`.`WORK_ESTIMATED`)), 2),
          NULL)                                                                                                       AS `progress`,
       `esc`.`LOCK_TIME`                                                                                              AS `lock_latency`,
       `esc`.`CPU_TIME`                                                                                               AS `cpu_latency`,
       `esc`.`ROWS_EXAMINED`                                                                                          AS `rows_examined`,
       `esc`.`ROWS_SENT`                                                                                              AS `rows_sent`,
       `esc`.`ROWS_AFFECTED`                                                                                          AS `rows_affected`,
       `esc`.`CREATED_TMP_TABLES`                                                                                     AS `tmp_tables`,
       `esc`.`CREATED_TMP_DISK_TABLES`                                                                                AS `tmp_disk_tables`,
       if(((`esc`.`NO_GOOD_INDEX_USED` > 0) or (`esc`.`NO_INDEX_USED` > 0)), 'YES',
          'NO')                                                                                                       AS `full_scan`,
       if((`esc`.`END_EVENT_ID` is not null), `esc`.`SQL_TEXT`, NULL)                                                 AS `last_statement`,
       if((`esc`.`END_EVENT_ID` is not null), `esc`.`TIMER_WAIT`, NULL)                                               AS `last_statement_latency`,
       `sys`.`mem`.`current_allocated`                                                                                AS `current_memory`,
       `ewc`.`EVENT_NAME`                                                                                             AS `last_wait`,
       if(((`ewc`.`END_EVENT_ID` is null) and (`ewc`.`EVENT_NAME` is not null)), 'Still Waiting',
          `ewc`.`TIMER_WAIT`)                                                                                         AS `last_wait_latency`,
       `ewc`.`SOURCE`                                                                                                 AS `source`,
       `etc`.`TIMER_WAIT`                                                                                             AS `trx_latency`,
       `etc`.`STATE`                                                                                                  AS `trx_state`,
       `etc`.`AUTOCOMMIT`                                                                                             AS `trx_autocommit`,
       `conattr_pid`.`ATTR_VALUE`                                                                                     AS `pid`,
       `conattr_progname`.`ATTR_VALUE`                                                                                AS `program_name`
from (((((((`performance_schema`.`threads` `pps` left join `performance_schema`.`events_waits_current` `ewc`
            on ((`pps`.`THREAD_ID` = `ewc`.`THREAD_ID`))) left join `performance_schema`.`events_stages_current` `estc`
           on ((`pps`.`THREAD_ID` = `estc`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `esc`
          on ((`pps`.`THREAD_ID` = `esc`.`THREAD_ID`))) left join `performance_schema`.`events_transactions_current` `etc`
         on ((`pps`.`THREAD_ID` = `etc`.`THREAD_ID`))) left join `sys`.`x$memory_by_thread_by_current_bytes` `mem`
        on ((`pps`.`THREAD_ID` = `sys`.`mem`.`thread_id`))) left join `performance_schema`.`session_connect_attrs` `conattr_pid`
       on (((`conattr_pid`.`PROCESSLIST_ID` = `pps`.`PROCESSLIST_ID`) and
            (`conattr_pid`.`ATTR_NAME` = '_pid')))) left join `performance_schema`.`session_connect_attrs` `conattr_progname`
      on (((`conattr_progname`.`PROCESSLIST_ID` = `pps`.`PROCESSLIST_ID`) and
           (`conattr_progname`.`ATTR_NAME` = 'program_name'))))
order by `pps`.`PROCESSLIST_TIME` desc, `last_wait_latency` desc;

