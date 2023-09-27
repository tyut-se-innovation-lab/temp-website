create view information_schema.STATISTICS as
select (`cat`.`name` collate utf8mb3_tolower_ci)                                                         AS `TABLE_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)                                                         AS `TABLE_SCHEMA`,
       (`tbl`.`name` collate utf8mb3_tolower_ci)                                                         AS `TABLE_NAME`,
       if(((`idx`.`type` = 'PRIMARY') or (`idx`.`type` = 'UNIQUE')), 0, 1)                               AS `NON_UNIQUE`,
       (`sch`.`name` collate utf8mb3_tolower_ci)                                                         AS `INDEX_SCHEMA`,
       (`idx`.`name` collate utf8mb3_tolower_ci)                                                         AS `INDEX_NAME`,
       `icu`.`ordinal_position`                                                                          AS `SEQ_IN_INDEX`,
       if((`col`.`hidden` = 'SQL'), NULL,
          (`col`.`name` collate utf8mb3_tolower_ci))                                                     AS `COLUMN_NAME`,
       (case when (`icu`.`order` = 'DESC') then 'D' when (`icu`.`order` = 'ASC') then 'A' else NULL end) AS `COLLATION`,
       internal_index_column_cardinality(`sch`.`name`, `tbl`.`name`, `idx`.`name`, `col`.`name`,
                                         `idx`.`ordinal_position`, `icu`.`ordinal_position`,
                                         if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                                         `tbl`.`se_private_id`,
                                         ((`tbl`.`hidden` <> 'Visible') or (0 <> `idx`.`hidden`) or
                                          (0 <> `icu`.`hidden`)),
                                         coalesce(`stat`.`cardinality`, cast(-(1) as unsigned)),
                                         coalesce(cast(`stat`.`cached_time` as unsigned), 0))            AS `CARDINALITY`,
       get_dd_index_sub_part_length(`icu`.`length`, `col`.`type`, `col`.`char_length`, `col`.`collation_id`,
                                    `idx`.`type`)                                                        AS `SUB_PART`,
       NULL                                                                                              AS `PACKED`,
       if((`col`.`is_nullable` = 1), 'YES', '')                                                          AS `NULLABLE`,
       (case
            when (`idx`.`type` = 'SPATIAL') then 'SPATIAL'
            when (`idx`.`algorithm` = 'SE_PRIVATE') then ''
            else `idx`.`algorithm` end)                                                                  AS `INDEX_TYPE`,
       if(((`idx`.`type` = 'PRIMARY') or (`idx`.`type` = 'UNIQUE')), '',
          if(internal_keys_disabled(`tbl`.`options`), 'disabled', ''))                                   AS `COMMENT`,
       `idx`.`comment`                                                                                   AS `INDEX_COMMENT`,
       if(`idx`.`is_visible`, 'YES', 'NO')                                                               AS `IS_VISIBLE`,
       if((`col`.`hidden` = 'SQL'), `col`.`generation_expression_utf8`, NULL)                            AS `EXPRESSION`
from (((((((`mysql`.`index_column_usage` `icu` join `mysql`.`indexes` `idx`
            on ((`idx`.`id` = `icu`.`index_id`))) join `mysql`.`tables` `tbl`
           on ((`idx`.`table_id` = `tbl`.`id`))) join `mysql`.`columns` `col`
          on ((`icu`.`column_id` = `col`.`id`))) join `mysql`.`schemata` `sch`
         on ((`tbl`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
        on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`collations` `coll`
       on ((`tbl`.`collation_id` = `coll`.`id`))) left join `mysql`.`index_stats` `stat`
      on (((`tbl`.`name` = `stat`.`table_name`) and (`sch`.`name` = `stat`.`schema_name`) and
           (`idx`.`name` = `stat`.`index_name`) and (`col`.`name` = `stat`.`column_name`))))
where ((0 <> can_access_table(`sch`.`name`, `tbl`.`name`)) and
       (0 <> is_visible_dd_object(`tbl`.`hidden`, ((0 <> `idx`.`hidden`) or (0 <> `icu`.`hidden`)), `idx`.`options`)));

