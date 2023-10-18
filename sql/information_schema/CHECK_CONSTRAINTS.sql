create view information_schema.CHECK_CONSTRAINTS as
select (`cat`.`name` collate utf8mb3_tolower_ci) AS `CONSTRAINT_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci) AS `CONSTRAINT_SCHEMA`,
       `cc`.`name`                               AS `CONSTRAINT_NAME`,
       `cc`.`check_clause_utf8`                  AS `CHECK_CLAUSE`
from (((`mysql`.`check_constraints` `cc` join `mysql`.`tables` `tbl`
        on ((`cc`.`table_id` = `tbl`.`id`))) join `mysql`.`schemata` `sch`
       on ((`tbl`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat` on ((`cat`.`id` = `sch`.`catalog_id`)))
where ((0 <> can_access_table(`sch`.`name`, `tbl`.`name`)) and (0 <> is_visible_dd_object(`tbl`.`hidden`)));

