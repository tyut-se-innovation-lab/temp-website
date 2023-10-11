create definer = `mysql.sys`@localhost view sys.x$user_summary as
select if((`performance_schema`.`accounts`.`USER` is null), 'background',
          `performance_schema`.`accounts`.`USER`)                                            AS `user`,
       sum(`sys`.`stmt`.`total`)                                                             AS `statements`,
       sum(`sys`.`stmt`.`total_latency`)                                                     AS `statement_latency`,
       ifnull((sum(`sys`.`stmt`.`total_latency`) / nullif(sum(`sys`.`stmt`.`total`), 0)), 0) AS `statement_avg_latency`,
       sum(`sys`.`stmt`.`full_scans`)                                                        AS `table_scans`,
       sum(`sys`.`io`.`ios`)                                                                 AS `file_ios`,
       sum(`sys`.`io`.`io_latency`)                                                          AS `file_io_latency`,
       sum(`performance_schema`.`accounts`.`CURRENT_CONNECTIONS`)                            AS `current_connections`,
       sum(`performance_schema`.`accounts`.`TOTAL_CONNECTIONS`)                              AS `total_connections`,
       count(distinct `performance_schema`.`accounts`.`HOST`)                                AS `unique_hosts`,
       sum(`sys`.`mem`.`current_allocated`)                                                  AS `current_memory`,
       sum(`sys`.`mem`.`total_allocated`)                                                    AS `total_memory_allocated`
from (((`performance_schema`.`accounts` left join `sys`.`x$user_summary_by_statement_latency` `stmt`
        on ((if((`performance_schema`.`accounts`.`USER` is null), 'background',
                `performance_schema`.`accounts`.`USER`) =
             `sys`.`stmt`.`user`))) left join `sys`.`x$user_summary_by_file_io` `io`
       on ((if((`performance_schema`.`accounts`.`USER` is null), 'background', `performance_schema`.`accounts`.`USER`) =
            `sys`.`io`.`user`))) left join `sys`.`x$memory_by_user_by_current_bytes` `mem`
      on ((if((`performance_schema`.`accounts`.`USER` is null), 'background', `performance_schema`.`accounts`.`USER`) =
           `sys`.`mem`.`user`)))
group by if((`performance_schema`.`accounts`.`USER` is null), 'background', `performance_schema`.`accounts`.`USER`)
order by sum(`sys`.`stmt`.`total_latency`) desc;

