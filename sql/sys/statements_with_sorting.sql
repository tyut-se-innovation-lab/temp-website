create definer = `mysql.sys`@localhost view sys.statements_with_sorting as
select `sys`.`format_statement`(`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`)        AS `query`,
       `performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME`                                  AS `db`,
       `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`                                   AS `exec_count`,
       format_pico_time(`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`)             AS `total_latency`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES`                        AS `sort_merge_passes`,
       round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES` /
                     nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0),
             0)                                                                                                  AS `avg_sort_merges`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_SCAN`                                AS `sorts_using_scans`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_RANGE`                               AS `sort_using_range`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS`                                AS `rows_sorted`,
       round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` /
                     nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0),
             0)                                                                                                  AS `avg_rows_sorted`,
       `performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN`                                   AS `first_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN`                                    AS `last_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`DIGEST`                                       AS `digest`
from `performance_schema`.`events_statements_summary_by_digest`
where (`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` > 0)
order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` desc;

