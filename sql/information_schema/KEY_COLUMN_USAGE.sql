create view information_schema.KEY_COLUMN_USAGE as
select (`cat`.`name` collate utf8mb3_tolower_ci)     AS `CONSTRAINT_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)     AS `CONSTRAINT_SCHEMA`,
       `constraints`.`CONSTRAINT_NAME`               AS `CONSTRAINT_NAME`,
       (`cat`.`name` collate utf8mb3_tolower_ci)     AS `TABLE_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)     AS `TABLE_SCHEMA`,
       (`tbl`.`name` collate utf8mb3_tolower_ci)     AS `TABLE_NAME`,
       (`col`.`name` collate utf8mb3_tolower_ci)     AS `COLUMN_NAME`,
       `constraints`.`ORDINAL_POSITION`              AS `ORDINAL_POSITION`,
       `constraints`.`POSITION_IN_UNIQUE_CONSTRAINT` AS `POSITION_IN_UNIQUE_CONSTRAINT`,
       `constraints`.`REFERENCED_TABLE_SCHEMA`       AS `REFERENCED_TABLE_SCHEMA`,
       `constraints`.`REFERENCED_TABLE_NAME`         AS `REFERENCED_TABLE_NAME`,
       `constraints`.`REFERENCED_COLUMN_NAME`        AS `REFERENCED_COLUMN_NAME`
from (((`mysql`.`tables` `tbl` join `mysql`.`schemata` `sch`
        on ((`tbl`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
       on ((`cat`.`id` = `sch`.`catalog_id`))) join (lateral (select `idx`.`name`                                     AS `CONSTRAINT_NAME`,
                                                                     `icu`.`ordinal_position`                         AS `ORDINAL_POSITION`,
                                                                     NULL                                             AS `POSITION_IN_UNIQUE_CONSTRAINT`,
                                                                     NULL                                             AS `REFERENCED_TABLE_SCHEMA`,
                                                                     NULL                                             AS `REFERENCED_TABLE_NAME`,
                                                                     NULL                                             AS `REFERENCED_COLUMN_NAME`,
                                                                     `icu`.`column_id`                                AS `column_id`,
                                                                     ((0 <> `idx`.`hidden`) or (0 <> `icu`.`hidden`)) AS `HIDDEN`
                                                              from (`mysql`.`indexes` `idx` join `mysql`.`index_column_usage` `icu`
                                                                    on ((`icu`.`index_id` = `idx`.`id`)))
                                                              where ((`idx`.`table_id` = `tbl`.`id`) and
                                                                     (`idx`.`type` in ('PRIMARY', 'UNIQUE')))
                                                              union all
                                                              select (`fk`.`name` collate utf8mb3_tolower_ci) AS `CONSTRAINT_NAME`,
                                                                     `fkcu`.`ordinal_position`                AS `ORDINAL_POSITION`,
                                                                     `fkcu`.`ordinal_position`                AS `POSITION_IN_UNIQUE_CONSTRAINT`,
                                                                     `fk`.`referenced_table_schema`           AS `REFERENCED_TABLE_SCHEMA`,
                                                                     `fk`.`referenced_table_name`             AS `REFERENCED_TABLE_NAME`,
                                                                     `fkcu`.`referenced_column_name`          AS `REFERENCED_COLUMN_NAME`,
                                                                     `fkcu`.`column_id`                       AS `column_id`,
                                                                     false                                    AS `HIDDEN`
                                                              from (`mysql`.`foreign_keys` `fk` join `mysql`.`foreign_key_column_usage` `fkcu`
                                                                    on ((`fkcu`.`foreign_key_id` = `fk`.`id`)))
                                                              where (`fk`.`table_id` = `tbl`.`id`)) `constraints` join `mysql`.`columns` `col`
                                                     on ((`constraints`.`column_id` = `col`.`id`))))
where ((0 <> can_access_column(`sch`.`name`, `tbl`.`name`, `col`.`name`)) and (0 <> is_visible_dd_object(`tbl`.`hidden`,
                                                                                                         ((`col`.`hidden` not in ('Visible', 'User')) or
                                                                                                          (0 <> `constraints`.`HIDDEN`)),
                                                                                                         `col`.`options`)));

