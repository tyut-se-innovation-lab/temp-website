create definer = `mysql.sys`@localhost view sys.x$ps_digest_avg_latency_distribution as
select count(0)                                                                                          AS `cnt`,
       round((`performance_schema`.`events_statements_summary_by_digest`.`AVG_TIMER_WAIT` / 1000000), 0) AS `avg_us`
from `performance_schema`.`events_statements_summary_by_digest`
group by `avg_us`;

