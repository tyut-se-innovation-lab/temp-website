create view information_schema.TABLES_EXTENSIONS as
select `cat`.`name`                       AS `TABLE_CATALOG`,
       `sch`.`name`                       AS `TABLE_SCHEMA`,
       `tbl`.`name`                       AS `TABLE_NAME`,
       `tbl`.`engine_attribute`           AS `ENGINE_ATTRIBUTE`,
       `tbl`.`secondary_engine_attribute` AS `SECONDARY_ENGINE_ATTRIBUTE`
from ((`mysql`.`tables` `tbl` join `mysql`.`schemata` `sch`
       on ((`tbl`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat` on ((`cat`.`id` = `sch`.`catalog_id`)))
where ((0 <> can_access_table(`sch`.`name`, `tbl`.`name`)) and (0 <> is_visible_dd_object(`tbl`.`hidden`)));

