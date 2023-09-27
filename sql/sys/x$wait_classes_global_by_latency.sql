create definer = `mysql.sys`@localhost view sys.x$wait_classes_global_by_latency as
select substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`, '/',
                       3)                                                                                         AS `event_class`,
       sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`)                         AS `total`,
       sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`)                     AS `total_latency`,
       min(`performance_schema`.`events_waits_summary_global_by_event_name`.`MIN_TIMER_WAIT`)                     AS `min_latency`,
       ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) /
               nullif(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`), 0)),
              0)                                                                                                  AS `avg_latency`,
       max(`performance_schema`.`events_waits_summary_global_by_event_name`.`MAX_TIMER_WAIT`)                     AS `max_latency`
from `performance_schema`.`events_waits_summary_global_by_event_name`
where ((`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` > 0) and
       (`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` <> 'idle'))
group by substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`, '/', 3)
order by sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) desc;

