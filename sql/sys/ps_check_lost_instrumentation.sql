create definer = `mysql.sys`@localhost view sys.ps_check_lost_instrumentation as
select `performance_schema`.`global_status`.`VARIABLE_NAME`  AS `variable_name`,
       `performance_schema`.`global_status`.`VARIABLE_VALUE` AS `variable_value`
from `performance_schema`.`global_status`
where ((`performance_schema`.`global_status`.`VARIABLE_NAME` like 'perf%lost') and
       (`performance_schema`.`global_status`.`VARIABLE_VALUE` > 0));

