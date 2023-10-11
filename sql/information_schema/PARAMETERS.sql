create view information_schema.PARAMETERS as
select (`cat`.`name` collate utf8mb3_tolower_ci)                                                 AS `SPECIFIC_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)                                                 AS `SPECIFIC_SCHEMA`,
       `rtn`.`name`                                                                              AS `SPECIFIC_NAME`,
       if((`rtn`.`type` = 'FUNCTION'), (`prm`.`ordinal_position` - 1), `prm`.`ordinal_position`) AS `ORDINAL_POSITION`,
       if(((`rtn`.`type` = 'FUNCTION') and (`prm`.`ordinal_position` = 1)), NULL, `prm`.`mode`)  AS `PARAMETER_MODE`,
       if(((`rtn`.`type` = 'FUNCTION') and (`prm`.`ordinal_position` = 1)), NULL, `prm`.`name`)  AS `PARAMETER_NAME`,
       substring_index(substring_index(`prm`.`data_type_utf8`, '(', 1), ' ', 1)                  AS `DATA_TYPE`,
       internal_dd_char_length(`prm`.`data_type`, `prm`.`char_length`, `col`.`name`,
                               0)                                                                AS `CHARACTER_MAXIMUM_LENGTH`,
       internal_dd_char_length(`prm`.`data_type`, `prm`.`char_length`, `col`.`name`,
                               1)                                                                AS `CHARACTER_OCTET_LENGTH`,
       `prm`.`numeric_precision`                                                                 AS `NUMERIC_PRECISION`,
       if((`prm`.`numeric_precision` is null), NULL, ifnull(`prm`.`numeric_scale`, 0))           AS `NUMERIC_SCALE`,
       `prm`.`datetime_precision`                                                                AS `DATETIME_PRECISION`,
       (case `prm`.`data_type`
            when 'MYSQL_TYPE_STRING' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_VAR_STRING' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_VARCHAR' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_TINY_BLOB' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_MEDIUM_BLOB' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_BLOB' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_LONG_BLOB' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_ENUM' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            when 'MYSQL_TYPE_SET' then if((`cs`.`name` = 'binary'), NULL, `cs`.`name`)
            else NULL end)                                                                       AS `CHARACTER_SET_NAME`,
       (case `prm`.`data_type`
            when 'MYSQL_TYPE_STRING' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            when 'MYSQL_TYPE_VAR_STRING' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            when 'MYSQL_TYPE_VARCHAR' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            when 'MYSQL_TYPE_TINY_BLOB' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            when 'MYSQL_TYPE_MEDIUM_BLOB' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            when 'MYSQL_TYPE_BLOB' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            when 'MYSQL_TYPE_LONG_BLOB' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            when 'MYSQL_TYPE_ENUM' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            when 'MYSQL_TYPE_SET' then if((`cs`.`name` = 'binary'), NULL, `col`.`name`)
            else NULL end)                                                                       AS `COLLATION_NAME`,
       `prm`.`data_type_utf8`                                                                    AS `DTD_IDENTIFIER`,
       `rtn`.`type`                                                                              AS `ROUTINE_TYPE`
from (((((`mysql`.`parameters` `prm` join `mysql`.`routines` `rtn`
          on ((`prm`.`routine_id` = `rtn`.`id`))) join `mysql`.`schemata` `sch`
         on ((`rtn`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
        on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`collations` `col`
       on ((`prm`.`collation_id` = `col`.`id`))) join `mysql`.`character_sets` `cs`
      on ((`col`.`character_set_id` = `cs`.`id`)))
where (0 <> can_access_routine(`sch`.`name`, `rtn`.`name`, `rtn`.`type`, `rtn`.`definer`, false));

