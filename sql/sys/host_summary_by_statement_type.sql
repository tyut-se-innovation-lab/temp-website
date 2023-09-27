create definer = `mysql.sys`@localhost view sys.host_summary_by_statement_type as
select if((`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST` is null), 'background',
          `performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`)                         AS `host`,
       substring_index(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`EVENT_NAME`, '/',
                       -(1))                                                                                     AS `statement`,
       `performance_schema`.`events_statements_summary_by_host_by_event_name`.`COUNT_STAR`                       AS `total`,
       format_pico_time(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,
       format_pico_time(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,
       format_pico_time(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_LOCK_TIME`)  AS `lock_latency`,
       format_pico_time(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_CPU_TIME`)   AS `cpu_latency`,
       `performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_SENT`                    AS `rows_sent`,
       `performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_EXAMINED`                AS `rows_examined`,
       `performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_AFFECTED`                AS `rows_affected`,
       (`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_INDEX_USED` +
        `performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_GOOD_INDEX_USED`)         AS `full_scans`
from `performance_schema`.`events_statements_summary_by_host_by_event_name`
where (`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` <> 0)
order by if((`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST` is null), 'background',
            `performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),
         `performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc;

