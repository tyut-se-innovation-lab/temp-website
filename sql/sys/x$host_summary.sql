create definer = `mysql.sys`@localhost view sys.x$host_summary as
select if((`performance_schema`.`accounts`.`HOST` is null), 'background',
          `performance_schema`.`accounts`.`HOST`)                      AS `host`,
       sum(`sys`.`stmt`.`total`)                                       AS `statements`,
       sum(`sys`.`stmt`.`total_latency`)                               AS `statement_latency`,
       (sum(`sys`.`stmt`.`total_latency`) / sum(`sys`.`stmt`.`total`)) AS `statement_avg_latency`,
       sum(`sys`.`stmt`.`full_scans`)                                  AS `table_scans`,
       sum(`sys`.`io`.`ios`)                                           AS `file_ios`,
       sum(`sys`.`io`.`io_latency`)                                    AS `file_io_latency`,
       sum(`performance_schema`.`accounts`.`CURRENT_CONNECTIONS`)      AS `current_connections`,
       sum(`performance_schema`.`accounts`.`TOTAL_CONNECTIONS`)        AS `total_connections`,
       count(distinct `performance_schema`.`accounts`.`USER`)          AS `unique_users`,
       sum(`sys`.`mem`.`current_allocated`)                            AS `current_memory`,
       sum(`sys`.`mem`.`total_allocated`)                              AS `total_memory_allocated`
from (((`performance_schema`.`accounts` join `sys`.`x$host_summary_by_statement_latency` `stmt`
        on ((`performance_schema`.`accounts`.`HOST` = `sys`.`stmt`.`host`))) join `sys`.`x$host_summary_by_file_io` `io`
       on ((`performance_schema`.`accounts`.`HOST` = `sys`.`io`.`host`))) join `sys`.`x$memory_by_host_by_current_bytes` `mem`
      on ((`performance_schema`.`accounts`.`HOST` = `sys`.`mem`.`host`)))
group by if((`performance_schema`.`accounts`.`HOST` is null), 'background', `performance_schema`.`accounts`.`HOST`);

