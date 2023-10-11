create definer = `mysql.sys`@localhost view sys.statements_with_runtimes_in_95th_percentile as
select `sys`.`format_statement`(`stmts`.`DIGEST_TEXT`)                                            AS `query`,
       `stmts`.`SCHEMA_NAME`                                                                      AS `db`,
       if(((`stmts`.`SUM_NO_GOOD_INDEX_USED` > 0) or (`stmts`.`SUM_NO_INDEX_USED` > 0)), '*', '') AS `full_scan`,
       `stmts`.`COUNT_STAR`                                                                       AS `exec_count`,
       `stmts`.`SUM_ERRORS`                                                                       AS `err_count`,
       `stmts`.`SUM_WARNINGS`                                                                     AS `warn_count`,
       format_pico_time(`stmts`.`SUM_TIMER_WAIT`)                                                 AS `total_latency`,
       format_pico_time(`stmts`.`MAX_TIMER_WAIT`)                                                 AS `max_latency`,
       format_pico_time(`stmts`.`AVG_TIMER_WAIT`)                                                 AS `avg_latency`,
       `stmts`.`SUM_ROWS_SENT`                                                                    AS `rows_sent`,
       round(ifnull((`stmts`.`SUM_ROWS_SENT` / nullif(`stmts`.`COUNT_STAR`, 0)), 0), 0)           AS `rows_sent_avg`,
       `stmts`.`SUM_ROWS_EXAMINED`                                                                AS `rows_examined`,
       round(ifnull((`stmts`.`SUM_ROWS_EXAMINED` / nullif(`stmts`.`COUNT_STAR`, 0)), 0),
             0)                                                                                   AS `rows_examined_avg`,
       `stmts`.`FIRST_SEEN`                                                                       AS `first_seen`,
       `stmts`.`LAST_SEEN`                                                                        AS `last_seen`,
       `stmts`.`DIGEST`                                                                           AS `digest`
from (`performance_schema`.`events_statements_summary_by_digest` `stmts` join `sys`.`x$ps_digest_95th_percentile_by_avg_us` `top_percentile`
      on ((round((`stmts`.`AVG_TIMER_WAIT` / 1000000), 0) >= `sys`.`top_percentile`.`avg_us`)))
order by `stmts`.`AVG_TIMER_WAIT` desc;

