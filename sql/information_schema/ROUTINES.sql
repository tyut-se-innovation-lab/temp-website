create view information_schema.ROUTINES as
select `rtn`.`name`                                                                                           AS `SPECIFIC_NAME`,
       (`cat`.`name` collate utf8mb3_tolower_ci)                                                              AS `ROUTINE_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)                                                              AS `ROUTINE_SCHEMA`,
       `rtn`.`name`                                                                                           AS `ROUTINE_NAME`,
       `rtn`.`type`                                                                                           AS `ROUTINE_TYPE`,
       if((`rtn`.`type` = 'PROCEDURE'), '', substring_index(substring_index(`rtn`.`result_data_type_utf8`, '(', 1), ' ',
                                                            1))                                               AS `DATA_TYPE`,
       internal_dd_char_length(`rtn`.`result_data_type`, `rtn`.`result_char_length`, `coll_result`.`name`,
                               0)                                                                             AS `CHARACTER_MAXIMUM_LENGTH`,
       internal_dd_char_length(`rtn`.`result_data_type`, `rtn`.`result_char_length`, `coll_result`.`name`,
                               1)                                                                             AS `CHARACTER_OCTET_LENGTH`,
       `rtn`.`result_numeric_precision`                                                                       AS `NUMERIC_PRECISION`,
       `rtn`.`result_numeric_scale`                                                                           AS `NUMERIC_SCALE`,
       `rtn`.`result_datetime_precision`                                                                      AS `DATETIME_PRECISION`,
       (case `rtn`.`result_data_type`
            when 'MYSQL_TYPE_STRING' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            when 'MYSQL_TYPE_VAR_STRING' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            when 'MYSQL_TYPE_VARCHAR' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            when 'MYSQL_TYPE_TINY_BLOB' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            when 'MYSQL_TYPE_MEDIUM_BLOB' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            when 'MYSQL_TYPE_BLOB' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            when 'MYSQL_TYPE_LONG_BLOB' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            when 'MYSQL_TYPE_ENUM' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            when 'MYSQL_TYPE_SET' then if((`cs_result`.`name` = 'binary'), NULL, `cs_result`.`name`)
            else NULL end)                                                                                    AS `CHARACTER_SET_NAME`,
       (case `rtn`.`result_data_type`
            when 'MYSQL_TYPE_STRING' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            when 'MYSQL_TYPE_VAR_STRING' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            when 'MYSQL_TYPE_VARCHAR' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            when 'MYSQL_TYPE_TINY_BLOB' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            when 'MYSQL_TYPE_MEDIUM_BLOB' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            when 'MYSQL_TYPE_BLOB' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            when 'MYSQL_TYPE_LONG_BLOB' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            when 'MYSQL_TYPE_ENUM' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            when 'MYSQL_TYPE_SET' then if((`cs_result`.`name` = 'binary'), NULL, `coll_result`.`name`)
            else NULL end)                                                                                    AS `COLLATION_NAME`,
       if((`rtn`.`type` = 'PROCEDURE'), NULL,
          `rtn`.`result_data_type_utf8`)                                                                      AS `DTD_IDENTIFIER`,
       'SQL'                                                                                                  AS `ROUTINE_BODY`,
       if(can_access_routine(`sch`.`name`, `rtn`.`name`, `rtn`.`type`, `rtn`.`definer`, true), `rtn`.`definition_utf8`,
          NULL)                                                                                               AS `ROUTINE_DEFINITION`,
       NULL                                                                                                   AS `EXTERNAL_NAME`,
       `rtn`.`external_language`                                                                              AS `EXTERNAL_LANGUAGE`,
       'SQL'                                                                                                  AS `PARAMETER_STYLE`,
       if((`rtn`.`is_deterministic` = 0), 'NO', 'YES')                                                        AS `IS_DETERMINISTIC`,
       `rtn`.`sql_data_access`                                                                                AS `SQL_DATA_ACCESS`,
       NULL                                                                                                   AS `SQL_PATH`,
       `rtn`.`security_type`                                                                                  AS `SECURITY_TYPE`,
       `rtn`.`created`                                                                                        AS `CREATED`,
       `rtn`.`last_altered`                                                                                   AS `LAST_ALTERED`,
       `rtn`.`sql_mode`                                                                                       AS `SQL_MODE`,
       `rtn`.`comment`                                                                                        AS `ROUTINE_COMMENT`,
       `rtn`.`definer`                                                                                        AS `DEFINER`,
       `cs_client`.`name`                                                                                     AS `CHARACTER_SET_CLIENT`,
       `coll_conn`.`name`                                                                                     AS `COLLATION_CONNECTION`,
       `coll_db`.`name`                                                                                       AS `DATABASE_COLLATION`
from ((((((((`mysql`.`routines` `rtn` join `mysql`.`schemata` `sch`
             on ((`rtn`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
            on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`collations` `coll_client`
           on ((`coll_client`.`id` = `rtn`.`client_collation_id`))) join `mysql`.`character_sets` `cs_client`
          on ((`cs_client`.`id` = `coll_client`.`character_set_id`))) join `mysql`.`collations` `coll_conn`
         on ((`coll_conn`.`id` = `rtn`.`connection_collation_id`))) join `mysql`.`collations` `coll_db`
        on ((`coll_db`.`id` = `rtn`.`schema_collation_id`))) left join `mysql`.`collations` `coll_result`
       on ((`coll_result`.`id` = `rtn`.`result_collation_id`))) left join `mysql`.`character_sets` `cs_result`
      on ((`cs_result`.`id` = `coll_result`.`character_set_id`)))
where (0 <> can_access_routine(`sch`.`name`, `rtn`.`name`, `rtn`.`type`, `rtn`.`definer`, false));

