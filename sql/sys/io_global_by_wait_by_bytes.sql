create definer = `mysql.sys`@localhost view sys.io_global_by_wait_by_bytes as
select substring_index(`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME`, '/', -(2))            AS `event_name`,
       `performance_schema`.`file_summary_by_event_name`.`COUNT_STAR`                                        AS `total`,
       format_pico_time(`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WAIT`)                  AS `total_latency`,
       format_pico_time(`performance_schema`.`file_summary_by_event_name`.`MIN_TIMER_WAIT`)                  AS `min_latency`,
       format_pico_time(`performance_schema`.`file_summary_by_event_name`.`AVG_TIMER_WAIT`)                  AS `avg_latency`,
       format_pico_time(`performance_schema`.`file_summary_by_event_name`.`MAX_TIMER_WAIT`)                  AS `max_latency`,
       `performance_schema`.`file_summary_by_event_name`.`COUNT_READ`                                        AS `count_read`,
       format_bytes(`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`)            AS `total_read`,
       format_bytes(ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ` /
                            nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_READ`, 0)),
                           0))                                                                               AS `avg_read`,
       `performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE`                                       AS `count_write`,
       format_bytes(`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE`)           AS `total_written`,
       format_bytes(ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` /
                            nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE`, 0)),
                           0))                                                                               AS `avg_written`,
       format_bytes((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` +
                     `performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`))          AS `total_requested`
from `performance_schema`.`file_summary_by_event_name`
where ((`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') and
       (`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` > 0))
order by (`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` +
          `performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`) desc;

