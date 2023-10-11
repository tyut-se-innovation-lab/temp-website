create definer = `mysql.sys`@localhost view sys.statements_with_errors_or_warnings as
select `sys`.`format_statement`(`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`)      AS `query`,
       `performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME`                                AS `db`,
       `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`                                 AS `exec_count`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS`                                 AS `errors`,
       (ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` /
                nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0) *
        100)                                                                                                   AS `error_pct`,
       `performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS`                               AS `warnings`,
       (ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` /
                nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`, 0)), 0) *
        100)                                                                                                   AS `warning_pct`,
       `performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN`                                 AS `first_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN`                                  AS `last_seen`,
       `performance_schema`.`events_statements_summary_by_digest`.`DIGEST`                                     AS `digest`
from `performance_schema`.`events_statements_summary_by_digest`
where ((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` > 0) or
       (`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` > 0))
order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` desc,
         `performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` desc;

