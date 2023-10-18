create definer = `mysql.sys`@localhost view sys.x$statements_with_temp_tables as
select `performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`                                  AS `query`,
       `performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME`                                  AS `db`,
       `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`                                   AS `exec_count`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`                               AS `total_latency`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES`                       AS `memory_tmp_tables`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES`                  AS `disk_tmp_tables`,
       round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` /
                     nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0),
             0)                                                                                                  AS `avg_tmp_tables_per_query`,
       round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` /
                      nullif(`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES`, 0)),
                     0) * 100),
             0)                                                                                                  AS `tmp_tables_to_disk_pct`,
       `performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN`                                   AS `first_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN`                                    AS `last_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`DIGEST`                                       AS `digest`
from `performance_schema`.`events_statements_summary_by_digest`
where (`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` > 0)
order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` desc,
         `performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` desc;

