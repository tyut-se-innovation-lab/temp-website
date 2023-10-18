create definer = `mysql.sys`@localhost view sys.x$memory_by_user_by_current_bytes as
select if((`performance_schema`.`memory_summary_by_user_by_event_name`.`USER` is null), 'background',
          `performance_schema`.`memory_summary_by_user_by_event_name`.`USER`)                                        AS `user`,
       sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_COUNT_USED`)                         AS `current_count_used`,
       sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)               AS `current_allocated`,
       ifnull((sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) /
               nullif(sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_COUNT_USED`), 0)),
              0)                                                                                                     AS `current_avg_alloc`,
       max(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)               AS `current_max_alloc`,
       sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`SUM_NUMBER_OF_BYTES_ALLOC`)                  AS `total_allocated`
from `performance_schema`.`memory_summary_by_user_by_event_name`
group by if((`performance_schema`.`memory_summary_by_user_by_event_name`.`USER` is null), 'background',
            `performance_schema`.`memory_summary_by_user_by_event_name`.`USER`)
order by sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) desc;

