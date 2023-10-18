create definer = `mysql.sys`@localhost view sys.wait_classes_global_by_avg_latency as
select substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`, '/',
                       3)                                                                                                         AS `event_class`,
       sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`)                                         AS `total`,
       format_pico_time(cast(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) as unsigned)) AS `total_latency`,
       format_pico_time(min(`performance_schema`.`events_waits_summary_global_by_event_name`.`MIN_TIMER_WAIT`))                   AS `min_latency`,
       format_pico_time(ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) /
                                nullif(
                                        sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`),
                                        0)),
                               0))                                                                                                AS `avg_latency`,
       format_pico_time(cast(max(`performance_schema`.`events_waits_summary_global_by_event_name`.`MAX_TIMER_WAIT`) as unsigned)) AS `max_latency`
from `performance_schema`.`events_waits_summary_global_by_event_name`
where ((`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` > 0) and
       (`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` <> 'idle'))
group by `event_class`
order by ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) /
                 nullif(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`), 0)),
                0) desc;

