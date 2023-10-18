create definer = `mysql.sys`@localhost view sys.memory_by_thread_by_current_bytes as
select `mt`.`THREAD_ID`                                       AS `thread_id`,
       if((`t`.`NAME` = 'thread/sql/one_connection'),
          concat(`t`.`PROCESSLIST_USER`, '@', convert(`t`.`PROCESSLIST_HOST` using utf8mb4)),
          replace(`t`.`NAME`, 'thread/', ''))                 AS `user`,
       sum(`mt`.`CURRENT_COUNT_USED`)                         AS `current_count_used`,
       format_bytes(sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_allocated`,
       format_bytes(ifnull((sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`) / nullif(sum(`mt`.`CURRENT_COUNT_USED`), 0)),
                           0))                                AS `current_avg_alloc`,
       format_bytes(max(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_max_alloc`,
       format_bytes(sum(`mt`.`SUM_NUMBER_OF_BYTES_ALLOC`))    AS `total_allocated`
from (`performance_schema`.`memory_summary_by_thread_by_event_name` `mt` join `performance_schema`.`threads` `t`
      on ((`mt`.`THREAD_ID` = `t`.`THREAD_ID`)))
group by `mt`.`THREAD_ID`,
         if((`t`.`NAME` = 'thread/sql/one_connection'),
            concat(`t`.`PROCESSLIST_USER`, '@', convert(`t`.`PROCESSLIST_HOST` using utf8mb4)),
            replace(`t`.`NAME`, 'thread/', ''))
order by sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`) desc;

