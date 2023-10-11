create definer = `mysql.sys`@localhost view sys.memory_global_total as
select format_bytes(sum(`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `total_allocated`
from `performance_schema`.`memory_summary_global_by_event_name`;

