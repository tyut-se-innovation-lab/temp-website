create definer = `mysql.sys`@localhost view sys.x$ps_schema_table_statistics_io as
select `extract_schema_from_file_name`(`performance_schema`.`file_summary_by_instance`.`FILE_NAME`) AS `table_schema`,
       `extract_table_from_file_name`(`performance_schema`.`file_summary_by_instance`.`FILE_NAME`)  AS `table_name`,
       sum(`performance_schema`.`file_summary_by_instance`.`COUNT_READ`)                            AS `count_read`,
       sum(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ`)              AS `sum_number_of_bytes_read`,
       sum(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_READ`)                        AS `sum_timer_read`,
       sum(`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`)                           AS `count_write`,
       sum(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`)             AS `sum_number_of_bytes_write`,
       sum(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WRITE`)                       AS `sum_timer_write`,
       sum(`performance_schema`.`file_summary_by_instance`.`COUNT_MISC`)                            AS `count_misc`,
       sum(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_MISC`)                        AS `sum_timer_misc`
from `performance_schema`.`file_summary_by_instance`
group by `table_schema`, `table_name`;

