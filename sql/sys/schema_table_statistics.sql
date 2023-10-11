create definer = `mysql.sys`@localhost view sys.schema_table_statistics as
select `pst`.`OBJECT_SCHEMA`                                  AS `table_schema`,
       `pst`.`OBJECT_NAME`                                    AS `table_name`,
       format_pico_time(`pst`.`SUM_TIMER_WAIT`)               AS `total_latency`,
       `pst`.`COUNT_FETCH`                                    AS `rows_fetched`,
       format_pico_time(`pst`.`SUM_TIMER_FETCH`)              AS `fetch_latency`,
       `pst`.`COUNT_INSERT`                                   AS `rows_inserted`,
       format_pico_time(`pst`.`SUM_TIMER_INSERT`)             AS `insert_latency`,
       `pst`.`COUNT_UPDATE`                                   AS `rows_updated`,
       format_pico_time(`pst`.`SUM_TIMER_UPDATE`)             AS `update_latency`,
       `pst`.`COUNT_DELETE`                                   AS `rows_deleted`,
       format_pico_time(`pst`.`SUM_TIMER_DELETE`)             AS `delete_latency`,
       `sys`.`fsbi`.`count_read`                              AS `io_read_requests`,
       format_bytes(`sys`.`fsbi`.`sum_number_of_bytes_read`)  AS `io_read`,
       format_pico_time(`sys`.`fsbi`.`sum_timer_read`)        AS `io_read_latency`,
       `sys`.`fsbi`.`count_write`                             AS `io_write_requests`,
       format_bytes(`sys`.`fsbi`.`sum_number_of_bytes_write`) AS `io_write`,
       format_pico_time(`sys`.`fsbi`.`sum_timer_write`)       AS `io_write_latency`,
       `sys`.`fsbi`.`count_misc`                              AS `io_misc_requests`,
       format_pico_time(`sys`.`fsbi`.`sum_timer_misc`)        AS `io_misc_latency`
from (`performance_schema`.`table_io_waits_summary_by_table` `pst` left join `sys`.`x$ps_schema_table_statistics_io` `fsbi`
      on (((`pst`.`OBJECT_SCHEMA` = `sys`.`fsbi`.`table_schema`) and
           (`pst`.`OBJECT_NAME` = `sys`.`fsbi`.`table_name`))))
order by `pst`.`SUM_TIMER_WAIT` desc;

