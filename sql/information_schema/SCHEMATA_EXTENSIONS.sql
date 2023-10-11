create view information_schema.SCHEMATA_EXTENSIONS as
select (`cat`.`name` collate utf8mb3_tolower_ci) AS `CATALOG_NAME`,
       (`sch`.`name` collate utf8mb3_tolower_ci) AS `SCHEMA_NAME`,
       get_dd_schema_options(`sch`.`options`)    AS `OPTIONS`
from (`mysql`.`schemata` `sch` join `mysql`.`catalogs` `cat` on ((`cat`.`id` = `sch`.`catalog_id`)))
where (0 <> can_access_database(`sch`.`name`));

