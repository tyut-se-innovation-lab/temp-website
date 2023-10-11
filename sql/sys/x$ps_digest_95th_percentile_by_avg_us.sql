create definer = `mysql.sys`@localhost view sys.x$ps_digest_95th_percentile_by_avg_us as
select `sys`.`s2`.`avg_us`                                                                                       AS `avg_us`,
       ifnull((sum(`sys`.`s1`.`cnt`) /
               nullif((select count(0) from `performance_schema`.`events_statements_summary_by_digest`), 0)),
              0)                                                                                                 AS `percentile`
from (`sys`.`x$ps_digest_avg_latency_distribution` `s1` join `sys`.`x$ps_digest_avg_latency_distribution` `s2`
      on ((`sys`.`s1`.`avg_us` <= `sys`.`s2`.`avg_us`)))
group by `sys`.`s2`.`avg_us`
having (ifnull((sum(`sys`.`s1`.`cnt`) /
                nullif((select count(0) from `performance_schema`.`events_statements_summary_by_digest`), 0)), 0) >
        0.95)
order by `percentile`
limit 1;

