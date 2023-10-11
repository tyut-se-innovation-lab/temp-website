create definer = `mysql.sys`@localhost view sys.schema_unused_indexes as
select `t`.`OBJECT_SCHEMA` AS `object_schema`, `t`.`OBJECT_NAME` AS `object_name`, `t`.`INDEX_NAME` AS `index_name`
from (`performance_schema`.`table_io_waits_summary_by_index_usage` `t` join `information_schema`.`STATISTICS` `s`
      on (((`t`.`OBJECT_SCHEMA` = `information_schema`.`s`.`TABLE_SCHEMA`) and
           (`t`.`OBJECT_NAME` = `information_schema`.`s`.`TABLE_NAME`) and
           (`t`.`INDEX_NAME` = `information_schema`.`s`.`INDEX_NAME`))))
where ((`t`.`INDEX_NAME` is not null) and (`t`.`COUNT_STAR` = 0) and (`t`.`OBJECT_SCHEMA` <> 'mysql') and
       (`t`.`INDEX_NAME` <> 'PRIMARY') and (`information_schema`.`s`.`NON_UNIQUE` = 1) and
       (`information_schema`.`s`.`SEQ_IN_INDEX` = 1))
order by `t`.`OBJECT_SCHEMA`, `t`.`OBJECT_NAME`;

