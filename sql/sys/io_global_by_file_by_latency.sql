create definer = `mysql.sys`@localhost view sys.io_global_by_file_by_latency as
select `sys`.`format_path`(`performance_schema`.`file_summary_by_instance`.`FILE_NAME`)    AS `file`,
       `performance_schema`.`file_summary_by_instance`.`COUNT_STAR`                        AS `total`,
       format_pico_time(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WAIT`)  AS `total_latency`,
       `performance_schema`.`file_summary_by_instance`.`COUNT_READ`                        AS `count_read`,
       format_pico_time(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_READ`)  AS `read_latency`,
       `performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`                       AS `count_write`,
       format_pico_time(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WRITE`) AS `write_latency`,
       `performance_schema`.`file_summary_by_instance`.`COUNT_MISC`                        AS `count_misc`,
       format_pico_time(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_MISC`)  AS `misc_latency`
from `performance_schema`.`file_summary_by_instance`
order by `performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WAIT` desc;

