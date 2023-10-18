create definer = `mysql.sys`@localhost view sys.statements_with_full_table_scans as
select `sys`.`format_statement`(`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`) AS `query`,
       `performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME`                           AS `db`,
       `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`                            AS `exec_count`,
       format_pico_time(`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`)      AS `total_latency`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED`                     AS `no_index_used_count`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED`                AS `no_good_index_used_count`,
       round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` /
                      nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0) * 100),
             0)                                                                                           AS `no_index_used_pct`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT`                         AS `rows_sent`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED`                     AS `rows_examined`,
       round((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` /
              `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`),
             0)                                                                                           AS `rows_sent_avg`,
       round((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` /
              `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`),
             0)                                                                                           AS `rows_examined_avg`,
       `performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN`                            AS `first_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN`                             AS `last_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`DIGEST`                                AS `digest`
from `performance_schema`.`events_statements_summary_by_digest`
where (((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` > 0) or
        (`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED` > 0)) and
       (not ((`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT` like 'SHOW%'))))
order by round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` /
                        nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0) * 100),
               0) desc,
         format_pico_time(`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`) desc;

