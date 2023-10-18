create definer = `mysql.sys`@localhost view sys.x$schema_table_statistics_with_buffer as
select `pst`.`OBJECT_SCHEMA`                          AS `table_schema`,
       `pst`.`OBJECT_NAME`                            AS `table_name`,
       `pst`.`COUNT_FETCH`                            AS `rows_fetched`,
       `pst`.`SUM_TIMER_FETCH`                        AS `fetch_latency`,
       `pst`.`COUNT_INSERT`                           AS `rows_inserted`,
       `pst`.`SUM_TIMER_INSERT`                       AS `insert_latency`,
       `pst`.`COUNT_UPDATE`                           AS `rows_updated`,
       `pst`.`SUM_TIMER_UPDATE`                       AS `update_latency`,
       `pst`.`COUNT_DELETE`                           AS `rows_deleted`,
       `pst`.`SUM_TIMER_DELETE`                       AS `delete_latency`,
       `sys`.`fsbi`.`count_read`                      AS `io_read_requests`,
       `sys`.`fsbi`.`sum_number_of_bytes_read`        AS `io_read`,
       `sys`.`fsbi`.`sum_timer_read`                  AS `io_read_latency`,
       `sys`.`fsbi`.`count_write`                     AS `io_write_requests`,
       `sys`.`fsbi`.`sum_number_of_bytes_write`       AS `io_write`,
       `sys`.`fsbi`.`sum_timer_write`                 AS `io_write_latency`,
       `sys`.`fsbi`.`count_misc`                      AS `io_misc_requests`,
       `sys`.`fsbi`.`sum_timer_misc`                  AS `io_misc_latency`,
       `sys`.`ibp`.`allocated`                        AS `innodb_buffer_allocated`,
       `sys`.`ibp`.`data`                             AS `innodb_buffer_data`,
       (`sys`.`ibp`.`allocated` - `sys`.`ibp`.`data`) AS `innodb_buffer_free`,
       `sys`.`ibp`.`pages`                            AS `innodb_buffer_pages`,
       `sys`.`ibp`.`pages_hashed`                     AS `innodb_buffer_pages_hashed`,
       `sys`.`ibp`.`pages_old`                        AS `innodb_buffer_pages_old`,
       `sys`.`ibp`.`rows_cached`                      AS `innodb_buffer_rows_cached`
from ((`performance_schema`.`table_io_waits_summary_by_table` `pst` left join `sys`.`x$ps_schema_table_statistics_io` `fsbi`
       on (((`pst`.`OBJECT_SCHEMA` = `sys`.`fsbi`.`table_schema`) and
            (`pst`.`OBJECT_NAME` = `sys`.`fsbi`.`table_name`)))) left join `sys`.`x$innodb_buffer_stats_by_table` `ibp`
      on (((`pst`.`OBJECT_SCHEMA` = `sys`.`ibp`.`object_schema`) and
           (`pst`.`OBJECT_NAME` = `sys`.`ibp`.`object_name`))))
order by `pst`.`SUM_TIMER_WAIT` desc;

