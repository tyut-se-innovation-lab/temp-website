create definer = `mysql.sys`@localhost view sys.memory_by_host_by_current_bytes as
select if((`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST` is null), 'background',
          `performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`)                                        AS `host`,
       sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_COUNT_USED`)                         AS `current_count_used`,
       format_bytes(sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_allocated`,
       format_bytes(ifnull(
               (sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) /
                nullif(sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_COUNT_USED`), 0)),
               0))                                                                                                   AS `current_avg_alloc`,
       format_bytes(max(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_max_alloc`,
       format_bytes(sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`SUM_NUMBER_OF_BYTES_ALLOC`))    AS `total_allocated`
from `performance_schema`.`memory_summary_by_host_by_event_name`
group by if((`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST` is null), 'background',
            `performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`)
order by sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) desc;

