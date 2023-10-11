create view information_schema.VIEW_ROUTINE_USAGE as
select (`cat`.`name` collate utf8mb3_tolower_ci)            AS `TABLE_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)            AS `TABLE_SCHEMA`,
       (`vw`.`name` collate utf8mb3_tolower_ci)             AS `TABLE_NAME`,
       (`vru`.`routine_catalog` collate utf8mb3_tolower_ci) AS `SPECIFIC_CATALOG`,
       (`vru`.`routine_schema` collate utf8mb3_tolower_ci)  AS `SPECIFIC_SCHEMA`,
       `vru`.`routine_name`                                 AS `SPECIFIC_NAME`
from ((((`mysql`.`tables` `vw` join `mysql`.`schemata` `sch`
         on ((`vw`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
        on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`view_routine_usage` `vru`
       on ((`vru`.`view_id` = `vw`.`id`))) join `mysql`.`routines` `rtn`
      on (((`vru`.`routine_catalog` = `cat`.`name`) and (`vru`.`routine_schema` = `sch`.`name`) and
           (`vru`.`routine_name` = `rtn`.`name`))))
where ((`vw`.`type` = 'VIEW') and
       (0 <> can_access_routine(`vru`.`routine_schema`, `vru`.`routine_name`, `rtn`.`type`, `rtn`.`definer`, false)) and
       (0 <> can_access_view(`sch`.`name`, `vw`.`name`, `vw`.`view_definer`, `vw`.`options`)));

