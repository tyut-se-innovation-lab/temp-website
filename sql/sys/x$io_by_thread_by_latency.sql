create definer = `mysql.sys`@localhost view sys.x$io_by_thread_by_latency as
select if((`performance_schema`.`threads`.`PROCESSLIST_ID` is null),
          substring_index(`performance_schema`.`threads`.`NAME`, '/', -(1)),
          concat(`performance_schema`.`threads`.`PROCESSLIST_USER`, '@',
                 convert(`performance_schema`.`threads`.`PROCESSLIST_HOST` using utf8mb4)))      AS `user`,
       sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`COUNT_STAR`)     AS `total`,
       sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,
       min(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`MIN_TIMER_WAIT`) AS `min_latency`,
       avg(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency`,
       max(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,
       `performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID`           AS `thread_id`,
       `performance_schema`.`threads`.`PROCESSLIST_ID`                                           AS `processlist_id`
from (`performance_schema`.`events_waits_summary_by_thread_by_event_name` left join `performance_schema`.`threads`
      on ((`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID` =
           `performance_schema`.`threads`.`THREAD_ID`)))
where ((`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') and
       (`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT` > 0))
group by `performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID`,
         `performance_schema`.`threads`.`PROCESSLIST_ID`, `user`
order by sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT`) desc;

