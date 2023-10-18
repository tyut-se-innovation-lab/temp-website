create view information_schema.COLUMN_STATISTICS as
select `mysql`.`column_statistics`.`schema_name` AS `SCHEMA_NAME`,
       `mysql`.`column_statistics`.`table_name`  AS `TABLE_NAME`,
       `mysql`.`column_statistics`.`column_name` AS `COLUMN_NAME`,
       `mysql`.`column_statistics`.`histogram`   AS `HISTOGRAM`
from `mysql`.`column_statistics`
where (0 <> can_access_table(`mysql`.`column_statistics`.`schema_name`, `mysql`.`column_statistics`.`table_name`));

