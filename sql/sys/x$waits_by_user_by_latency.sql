create definer = `mysql.sys`@localhost view sys.x$waits_by_user_by_latency as
select if((`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER` is null), 'background',
          `performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`)       AS `user`,
       `performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME`     AS `event`,
       `performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR`     AS `total`,
       `performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,
       `performance_schema`.`events_waits_summary_by_user_by_event_name`.`AVG_TIMER_WAIT` AS `avg_latency`,
       `performance_schema`.`events_waits_summary_by_user_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency`
from `performance_schema`.`events_waits_summary_by_user_by_event_name`
where ((`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` <> 'idle') and
       (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER` is not null) and
       (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` > 0))
order by if((`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER` is null), 'background',
            `performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),
         `performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc;

