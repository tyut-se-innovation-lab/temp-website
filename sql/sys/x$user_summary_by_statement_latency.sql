create definer = `mysql.sys`@localhost view sys.x$user_summary_by_statement_latency as
select if((`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER` is null), 'background',
          `performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`)                      AS `user`,
       sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`COUNT_STAR`)               AS `total`,
       sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`)           AS `total_latency`,
       sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`MAX_TIMER_WAIT`)           AS `max_latency`,
       sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_LOCK_TIME`)            AS `lock_latency`,
       sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_CPU_TIME`)             AS `cpu_latency`,
       sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_SENT`)            AS `rows_sent`,
       sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_EXAMINED`)        AS `rows_examined`,
       sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_AFFECTED`)        AS `rows_affected`,
       (sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_INDEX_USED`) +
        sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_GOOD_INDEX_USED`)) AS `full_scans`
from `performance_schema`.`events_statements_summary_by_user_by_event_name`
group by if((`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER` is null), 'background',
            `performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`)
order by sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) desc;

