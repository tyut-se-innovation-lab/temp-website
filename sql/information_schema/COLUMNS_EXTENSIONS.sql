create view information_schema.COLUMNS_EXTENSIONS as
select `cat`.`name`                              AS `TABLE_CATALOG`,
       `sch`.`name`                              AS `TABLE_SCHEMA`,
       `tbl`.`name`                              AS `TABLE_NAME`,
       (`col`.`name` collate utf8mb3_tolower_ci) AS `COLUMN_NAME`,
       `col`.`engine_attribute`                  AS `ENGINE_ATTRIBUTE`,
       `col`.`secondary_engine_attribute`        AS `SECONDARY_ENGINE_ATTRIBUTE`
from (((`mysql`.`columns` `col` join `mysql`.`tables` `tbl`
        on ((`col`.`table_id` = `tbl`.`id`))) join `mysql`.`schemata` `sch`
       on ((`tbl`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat` on ((`cat`.`id` = `sch`.`catalog_id`)))
where ((0 <> internal_get_view_warning_or_error(`sch`.`name`, `tbl`.`name`, `tbl`.`type`, `tbl`.`options`)) and
       (0 <> can_access_column(`sch`.`name`, `tbl`.`name`, `col`.`name`)) and
       (0 <> is_visible_dd_object(`tbl`.`hidden`, (`col`.`hidden` not in ('Visible', 'User')), `col`.`options`)));

