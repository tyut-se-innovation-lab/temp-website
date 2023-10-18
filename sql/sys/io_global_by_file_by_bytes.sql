create definer = `mysql.sys`@localhost view sys.io_global_by_file_by_bytes as
select `sys`.`format_path`(`performance_schema`.`file_summary_by_instance`.`FILE_NAME`)                              AS `file`,
       `performance_schema`.`file_summary_by_instance`.`COUNT_READ`                                                  AS `count_read`,
       format_bytes(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ`)                      AS `total_read`,
       format_bytes(ifnull((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` /
                            nullif(`performance_schema`.`file_summary_by_instance`.`COUNT_READ`, 0)),
                           0))                                                                                       AS `avg_read`,
       `performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`                                                 AS `count_write`,
       format_bytes(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`)                     AS `total_written`,
       format_bytes(ifnull((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE` /
                            nullif(`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`, 0)),
                           0.00))                                                                                    AS `avg_write`,
       format_bytes((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` +
                     `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`))                   AS `total`,
       ifnull(round((100 - ((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` / nullif(
               (`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` +
                `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`), 0)) * 100)), 2),
              0.00)                                                                                                  AS `write_pct`
from `performance_schema`.`file_summary_by_instance`
order by (`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` +
          `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`) desc;

