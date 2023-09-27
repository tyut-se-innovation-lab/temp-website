create definer = `mysql.sys`@localhost view sys.x$memory_global_by_current_bytes as
select `performance_schema`.`memory_summary_global_by_event_name`.`EVENT_NAME`                                 AS `event_name`,
       `performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_COUNT_USED`                         AS `current_count`,
       `performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`               AS `current_alloc`,
       ifnull((`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` /
               nullif(`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_COUNT_USED`, 0)),
              0)                                                                                               AS `current_avg_alloc`,
       `performance_schema`.`memory_summary_global_by_event_name`.`HIGH_COUNT_USED`                            AS `high_count`,
       `performance_schema`.`memory_summary_global_by_event_name`.`HIGH_NUMBER_OF_BYTES_USED`                  AS `high_alloc`,
       ifnull((`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_NUMBER_OF_BYTES_USED` /
               nullif(`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_COUNT_USED`, 0)),
              0)                                                                                               AS `high_avg_alloc`
from `performance_schema`.`memory_summary_global_by_event_name`
where (`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` > 0)
order by `performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` desc;

