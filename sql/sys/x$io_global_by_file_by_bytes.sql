create definer = `mysql.sys`@localhost view sys.x$io_global_by_file_by_bytes as
select `performance_schema`.`file_summary_by_instance`.`FILE_NAME`                                                   AS `file`,
       `performance_schema`.`file_summary_by_instance`.`COUNT_READ`                                                  AS `count_read`,
       `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ`                                    AS `total_read`,
       ifnull((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` /
               nullif(`performance_schema`.`file_summary_by_instance`.`COUNT_READ`, 0)),
              0)                                                                                                     AS `avg_read`,
       `performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`                                                 AS `count_write`,
       `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`                                   AS `total_written`,
       ifnull((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE` /
               nullif(`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`, 0)),
              0.00)                                                                                                  AS `avg_write`,
       (`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` +
        `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`)                                 AS `total`,
       ifnull(round((100 - ((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` / nullif(
               (`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` +
                `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`), 0)) * 100)), 2),
              0.00)                                                                                                  AS `write_pct`
from `performance_schema`.`file_summary_by_instance`
order by (`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` +
          `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`) desc;

