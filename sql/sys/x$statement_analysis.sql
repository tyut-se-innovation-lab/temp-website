create definer = `mysql.sys`@localhost view sys.x$statement_analysis as
select `performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`                                  AS `query`,
       `performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME`                                  AS `db`,
       if(((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED` > 0) or
           (`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` > 0)), '*',
          '')                                                                                                    AS `full_scan`,
       `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`                                   AS `exec_count`,
       `performance_schema`.`events_statements_summary_by_digest`.`COUNT_SECONDARY`                              AS `exec_secondary_count`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS`                                   AS `err_count`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS`                                 AS `warn_count`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`                               AS `total_latency`,
       `performance_schema`.`events_statements_summary_by_digest`.`MAX_TIMER_WAIT`                               AS `max_latency`,
       `performance_schema`.`events_statements_summary_by_digest`.`AVG_TIMER_WAIT`                               AS `avg_latency`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_LOCK_TIME`                                AS `lock_latency`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_CPU_TIME`                                 AS `cpu_latency`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT`                                AS `rows_sent`,
       round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` /
                     nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0),
             0)                                                                                                  AS `rows_sent_avg`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED`                            AS `rows_examined`,
       round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` /
                     nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0),
             0)                                                                                                  AS `rows_examined_avg`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_AFFECTED`                            AS `rows_affected`,
       round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_AFFECTED` /
                     nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0),
             0)                                                                                                  AS `rows_affected_avg`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES`                       AS `tmp_tables`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES`                  AS `tmp_disk_tables`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS`                                AS `rows_sorted`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES`                        AS `sort_merge_passes`,
       `performance_schema`.`events_statements_summary_by_digest`.`MAX_CONTROLLED_MEMORY`                        AS `max_controlled_memory`,
       `performance_schema`.`events_statements_summary_by_digest`.`MAX_TOTAL_MEMORY`                             AS `max_total_memory`,
       `performance_schema`.`events_statements_summary_by_digest`.`DIGEST`                                       AS `digest`,
       `performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN`                                   AS `first_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN`                                    AS `last_seen`
from `performance_schema`.`events_statements_summary_by_digest`
order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` desc;

