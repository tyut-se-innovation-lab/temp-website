create definer = `mysql.sys`@localhost view sys.x$host_summary_by_file_io_type as
select if((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST` is null), 'background',
          `performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`)       AS `host`,
       `performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME`     AS `event_name`,
       `performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR`     AS `total`,
       `performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,
       `performance_schema`.`events_waits_summary_by_host_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency`
from `performance_schema`.`events_waits_summary_by_host_by_event_name`
where ((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` like 'wait/io/file%') and
       (`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR` > 0))
order by if((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST` is null), 'background',
            `performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),
         `performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc;

