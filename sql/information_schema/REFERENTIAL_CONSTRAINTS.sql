create view information_schema.REFERENTIAL_CONSTRAINTS as
select `cat`.`name`                             AS `CONSTRAINT_CATALOG`,
       `sch`.`name`                             AS `CONSTRAINT_SCHEMA`,
       (`fk`.`name` collate utf8mb3_tolower_ci) AS `CONSTRAINT_NAME`,
       `fk`.`referenced_table_catalog`          AS `UNIQUE_CONSTRAINT_CATALOG`,
       `fk`.`referenced_table_schema`           AS `UNIQUE_CONSTRAINT_SCHEMA`,
       `fk`.`unique_constraint_name`            AS `UNIQUE_CONSTRAINT_NAME`,
       `fk`.`match_option`                      AS `MATCH_OPTION`,
       `fk`.`update_rule`                       AS `UPDATE_RULE`,
       `fk`.`delete_rule`                       AS `DELETE_RULE`,
       `tbl`.`name`                             AS `TABLE_NAME`,
       `fk`.`referenced_table_name`             AS `REFERENCED_TABLE_NAME`
from (((`mysql`.`foreign_keys` `fk` join `mysql`.`tables` `tbl`
        on ((`fk`.`table_id` = `tbl`.`id`))) join `mysql`.`schemata` `sch`
       on ((`fk`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat` on ((`cat`.`id` = `sch`.`catalog_id`)))
where ((0 <> can_access_table(`sch`.`name`, `tbl`.`name`)) and (0 <> is_visible_dd_object(`tbl`.`hidden`)));

