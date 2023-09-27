create view information_schema.COLUMNS as
select (`cat`.`name` collate utf8mb3_tolower_ci)                                                          AS `TABLE_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)                                                          AS `TABLE_SCHEMA`,
       (`tbl`.`name` collate utf8mb3_tolower_ci)                                                          AS `TABLE_NAME`,
       (`col`.`name` collate utf8mb3_tolower_ci)                                                          AS `COLUMN_NAME`,
       `col`.`ordinal_position`                                                                           AS `ORDINAL_POSITION`,
       `col`.`default_value_utf8`                                                                         AS `COLUMN_DEFAULT`,
       if((`col`.`is_nullable` = 1), 'YES', 'NO')                                                         AS `IS_NULLABLE`,
       substring_index(substring_index(`col`.`column_type_utf8`, '(', 1), ' ',
                       1)                                                                                 AS `DATA_TYPE`,
       internal_dd_char_length(`col`.`type`, `col`.`char_length`, `coll`.`name`,
                               0)                                                                         AS `CHARACTER_MAXIMUM_LENGTH`,
       internal_dd_char_length(`col`.`type`, `col`.`char_length`, `coll`.`name`,
                               1)                                                                         AS `CHARACTER_OCTET_LENGTH`,
       if((`col`.`numeric_precision` = 0), NULL, `col`.`numeric_precision`)                               AS `NUMERIC_PRECISION`,
       if(((`col`.`numeric_scale` = 0) and (`col`.`numeric_precision` = 0)), NULL,
          `col`.`numeric_scale`)                                                                          AS `NUMERIC_SCALE`,
       `col`.`datetime_precision`                                                                         AS `DATETIME_PRECISION`,
       (case `col`.`type`
            when 'MYSQL_TYPE_STRING' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_VAR_STRING' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_VARCHAR' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_TINY_BLOB' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_MEDIUM_BLOB' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_BLOB' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_LONG_BLOB' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_ENUM' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_SET' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            else NULL end)                                                                                AS `CHARACTER_SET_NAME`,
       (case `col`.`type`
            when 'MYSQL_TYPE_STRING' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            when 'MYSQL_TYPE_VAR_STRING' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            when 'MYSQL_TYPE_VARCHAR' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            when 'MYSQL_TYPE_TINY_BLOB' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            when 'MYSQL_TYPE_MEDIUM_BLOB' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            when 'MYSQL_TYPE_BLOB' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            when 'MYSQL_TYPE_LONG_BLOB' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            when 'MYSQL_TYPE_ENUM' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            when 'MYSQL_TYPE_SET' then if((`cs`.`name` = 'binary'), NULL, `coll`.`name`)
            else NULL end)                                                                                AS `COLLATION_NAME`,
       `col`.`column_type_utf8`                                                                           AS `COLUMN_TYPE`,
       `col`.`column_key`                                                                                 AS `COLUMN_KEY`,
       internal_get_dd_column_extra((`col`.`generation_expression_utf8` is null), `col`.`is_virtual`,
                                    `col`.`is_auto_increment`, `col`.`update_option`,
                                    if(length(`col`.`default_option`), true, false), `col`.`options`, `col`.`hidden`,
                                    `tbl`.`type`)                                                         AS `EXTRA`,
       get_dd_column_privileges(`sch`.`name`, `tbl`.`name`, `col`.`name`)                                 AS `PRIVILEGES`,
       ifnull(`col`.`comment`, '')                                                                        AS `COLUMN_COMMENT`,
       ifnull(`col`.`generation_expression_utf8`, '')                                                     AS `GENERATION_EXPRESSION`,
       `col`.`srs_id`                                                                                     AS `SRS_ID`
from (((((`mysql`.`columns` `col` join `mysql`.`tables` `tbl`
          on ((`col`.`table_id` = `tbl`.`id`))) join `mysql`.`schemata` `sch`
         on ((`tbl`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
        on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`collations` `coll`
       on ((`col`.`collation_id` = `coll`.`id`))) join `mysql`.`character_sets` `cs`
      on ((`coll`.`character_set_id` = `cs`.`id`)))
where ((0 <> internal_get_view_warning_or_error(`sch`.`name`, `tbl`.`name`, `tbl`.`type`, `tbl`.`options`)) and
       (0 <> can_access_column(`sch`.`name`, `tbl`.`name`, `col`.`name`)) and
       (0 <> is_visible_dd_object(`tbl`.`hidden`, (`col`.`hidden` not in ('Visible', 'User')), `col`.`options`)));

