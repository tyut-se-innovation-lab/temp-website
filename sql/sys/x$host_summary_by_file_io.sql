create definer = `mysql.sys`@localhost view sys.x$host_summary_by_file_io as
select if((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST` is null), 'background',
          `performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`)            AS `host`,
       sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR`)     AS `ios`,
       sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) AS `io_latency`
from `performance_schema`.`events_waits_summary_by_host_by_event_name`
where (`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` like 'wait/io/file/%')
group by if((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST` is null), 'background',
            `performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`)
order by sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) desc;

