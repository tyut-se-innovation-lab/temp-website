create view information_schema.VIEW_TABLE_USAGE as
select (`cat`.`name` collate utf8mb3_tolower_ci)          AS `VIEW_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)          AS `VIEW_SCHEMA`,
       (`vw`.`name` collate utf8mb3_tolower_ci)           AS `VIEW_NAME`,
       (`vtu`.`table_catalog` collate utf8mb3_tolower_ci) AS `TABLE_CATALOG`,
       (`vtu`.`table_schema` collate utf8mb3_tolower_ci)  AS `TABLE_SCHEMA`,
       (`vtu`.`table_name` collate utf8mb3_tolower_ci)    AS `TABLE_NAME`
from (((`mysql`.`tables` `vw` join `mysql`.`schemata` `sch`
        on ((`vw`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
       on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`view_table_usage` `vtu` on ((`vtu`.`view_id` = `vw`.`id`)))
where ((0 <> can_access_table(`vtu`.`table_schema`, `vtu`.`table_name`)) and (`vw`.`type` = 'VIEW') and
       (0 <> can_access_view(`sch`.`name`, `vw`.`name`, `vw`.`view_definer`, `vw`.`options`)));

