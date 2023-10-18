create definer = `mysql.sys`@localhost view sys.schema_table_lock_waits as
select `g`.`OBJECT_SCHEMA`                               AS `object_schema`,
       `g`.`OBJECT_NAME`                                 AS `object_name`,
       `pt`.`THREAD_ID`                                  AS `waiting_thread_id`,
       `pt`.`PROCESSLIST_ID`                             AS `waiting_pid`,
       `sys`.`ps_thread_account`(`p`.`OWNER_THREAD_ID`)  AS `waiting_account`,
       `p`.`LOCK_TYPE`                                   AS `waiting_lock_type`,
       `p`.`LOCK_DURATION`                               AS `waiting_lock_duration`,
       `sys`.`format_statement`(`pt`.`PROCESSLIST_INFO`) AS `waiting_query`,
       `pt`.`PROCESSLIST_TIME`                           AS `waiting_query_secs`,
       `ps`.`ROWS_AFFECTED`                              AS `waiting_query_rows_affected`,
       `ps`.`ROWS_EXAMINED`                              AS `waiting_query_rows_examined`,
       `gt`.`THREAD_ID`                                  AS `blocking_thread_id`,
       `gt`.`PROCESSLIST_ID`                             AS `blocking_pid`,
       `sys`.`ps_thread_account`(`g`.`OWNER_THREAD_ID`)  AS `blocking_account`,
       `g`.`LOCK_TYPE`                                   AS `blocking_lock_type`,
       `g`.`LOCK_DURATION`                               AS `blocking_lock_duration`,
       concat('KILL QUERY ', `gt`.`PROCESSLIST_ID`)      AS `sql_kill_blocking_query`,
       concat('KILL ', `gt`.`PROCESSLIST_ID`)            AS `sql_kill_blocking_connection`
from (((((`performance_schema`.`metadata_locks` `g` join `performance_schema`.`metadata_locks` `p`
          on (((`g`.`OBJECT_TYPE` = `p`.`OBJECT_TYPE`) and (`g`.`OBJECT_SCHEMA` = `p`.`OBJECT_SCHEMA`) and
               (`g`.`OBJECT_NAME` = `p`.`OBJECT_NAME`) and (`g`.`LOCK_STATUS` = 'GRANTED') and
               (`p`.`LOCK_STATUS` = 'PENDING')))) join `performance_schema`.`threads` `gt`
         on ((`g`.`OWNER_THREAD_ID` = `gt`.`THREAD_ID`))) join `performance_schema`.`threads` `pt`
        on ((`p`.`OWNER_THREAD_ID` = `pt`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `gs`
       on ((`g`.`OWNER_THREAD_ID` = `gs`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `ps`
      on ((`p`.`OWNER_THREAD_ID` = `ps`.`THREAD_ID`)))
where (`g`.`OBJECT_TYPE` = 'TABLE');

