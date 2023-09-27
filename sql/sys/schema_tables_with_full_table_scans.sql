create definer = `mysql.sys`@localhost view sys.schema_tables_with_full_table_scans as
select `performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_SCHEMA`                    AS `object_schema`,
       `performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_NAME`                      AS `object_name`,
       `performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ`                       AS `rows_full_scanned`,
       format_pico_time(`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_WAIT`) AS `latency`
from `performance_schema`.`table_io_waits_summary_by_index_usage`
where ((`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME` is null) and
       (`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ` > 0))
order by `performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ` desc;

