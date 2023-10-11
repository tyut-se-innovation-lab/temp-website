create view information_schema.PARTITIONS as
select (`cat`.`name` collate utf8mb3_tolower_ci)                                                                     AS `TABLE_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)                                                                     AS `TABLE_SCHEMA`,
       `tbl`.`name`                                                                                                  AS `TABLE_NAME`,
       `part`.`name`                                                                                                 AS `PARTITION_NAME`,
       `sub_part`.`name`                                                                                             AS `SUBPARTITION_NAME`,
       (`part`.`number` + 1)                                                                                         AS `PARTITION_ORDINAL_POSITION`,
       (`sub_part`.`number` + 1)                                                                                     AS `SUBPARTITION_ORDINAL_POSITION`,
       (case `tbl`.`partition_type`
            when 'HASH' then 'HASH'
            when 'RANGE' then 'RANGE'
            when 'LIST' then 'LIST'
            when 'AUTO' then 'AUTO'
            when 'KEY_51' then 'KEY'
            when 'KEY_55' then 'KEY'
            when 'LINEAR_KEY_51' then 'LINEAR KEY'
            when 'LINEAR_KEY_55' then 'LINEAR KEY'
            when 'LINEAR_HASH' then 'LINEAR HASH'
            when 'RANGE_COLUMNS' then 'RANGE COLUMNS'
            when 'LIST_COLUMNS' then 'LIST COLUMNS'
            else NULL end)                                                                                           AS `PARTITION_METHOD`,
       (case `tbl`.`subpartition_type`
            when 'HASH' then 'HASH'
            when 'RANGE' then 'RANGE'
            when 'LIST' then 'LIST'
            when 'AUTO' then 'AUTO'
            when 'KEY_51' then 'KEY'
            when 'KEY_55' then 'KEY'
            when 'LINEAR_KEY_51' then 'LINEAR KEY'
            when 'LINEAR_KEY_55' then 'LINEAR KEY'
            when 'LINEAR_HASH' then 'LINEAR HASH'
            when 'RANGE_COLUMNS' then 'RANGE COLUMNS'
            when 'LIST_COLUMNS' then 'LIST COLUMNS'
            else NULL end)                                                                                           AS `SUBPARTITION_METHOD`,
       `tbl`.`partition_expression_utf8`                                                                             AS `PARTITION_EXPRESSION`,
       `tbl`.`subpartition_expression_utf8`                                                                          AS `SUBPARTITION_EXPRESSION`,
       `part`.`description_utf8`                                                                                     AS `PARTITION_DESCRIPTION`,
       internal_table_rows(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                           `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                    if((`part`.`name` is null),
                                                                                       `tbl`.`se_private_data`,
                                                                                       `part_ts`.`se_private_data`),
                                                                                    `sub_part_ts`.`se_private_data`), 0,
                           0,
                           ifnull(`sub_part`.`name`, `part`.`name`))                                                 AS `TABLE_ROWS`,
       internal_avg_row_length(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                               `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                        if((`part`.`name` is null),
                                                                                           `tbl`.`se_private_data`,
                                                                                           `part_ts`.`se_private_data`),
                                                                                        `sub_part_ts`.`se_private_data`),
                               0, 0,
                               ifnull(`sub_part`.`name`, `part`.`name`))                                             AS `AVG_ROW_LENGTH`,
       internal_data_length(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                            `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                     if((`part`.`name` is null),
                                                                                        `tbl`.`se_private_data`,
                                                                                        `part_ts`.`se_private_data`),
                                                                                     `sub_part_ts`.`se_private_data`),
                            0, 0,
                            ifnull(`sub_part`.`name`, `part`.`name`))                                                AS `DATA_LENGTH`,
       internal_max_data_length(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                                `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                         if((`part`.`name` is null),
                                                                                            `tbl`.`se_private_data`,
                                                                                            `part_ts`.`se_private_data`),
                                                                                         `sub_part_ts`.`se_private_data`),
                                0, 0,
                                ifnull(`sub_part`.`name`, `part`.`name`))                                            AS `MAX_DATA_LENGTH`,
       internal_index_length(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                             `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                      if((`part`.`name` is null),
                                                                                         `tbl`.`se_private_data`,
                                                                                         `part_ts`.`se_private_data`),
                                                                                      `sub_part_ts`.`se_private_data`),
                             0, 0,
                             ifnull(`sub_part`.`name`, `part`.`name`))                                               AS `INDEX_LENGTH`,
       internal_data_free(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                          `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                   if((`part`.`name` is null),
                                                                                      `tbl`.`se_private_data`,
                                                                                      `part_ts`.`se_private_data`),
                                                                                   `sub_part_ts`.`se_private_data`), 0,
                          0,
                          ifnull(`sub_part`.`name`, `part`.`name`))                                                  AS `DATA_FREE`,
       `tbl`.`created`                                                                                               AS `CREATE_TIME`,
       internal_update_time(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                            `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                     if((`part`.`name` is null),
                                                                                        `tbl`.`se_private_data`,
                                                                                        `part_ts`.`se_private_data`),
                                                                                     `sub_part_ts`.`se_private_data`),
                            0, 0,
                            ifnull(`sub_part`.`name`, `part`.`name`))                                                AS `UPDATE_TIME`,
       internal_check_time(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                           `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                    if((`part`.`name` is null),
                                                                                       `tbl`.`se_private_data`,
                                                                                       `part_ts`.`se_private_data`),
                                                                                    `sub_part_ts`.`se_private_data`), 0,
                           0,
                           ifnull(`sub_part`.`name`, `part`.`name`))                                                 AS `CHECK_TIME`,
       internal_checksum(`sch`.`name`, `tbl`.`name`, if((`tbl`.`partition_type` is null), `tbl`.`engine`, ''),
                         `tbl`.`se_private_id`, (`tbl`.`hidden` <> 'Visible'), if((`sub_part`.`name` is null),
                                                                                  if((`part`.`name` is null),
                                                                                     `tbl`.`se_private_data`,
                                                                                     `part_ts`.`se_private_data`),
                                                                                  `sub_part_ts`.`se_private_data`), 0,
                         0,
                         ifnull(`sub_part`.`name`, `part`.`name`))                                                   AS `CHECKSUM`,
       if((`sub_part`.`name` is null), ifnull(`part`.`comment`, ''),
          ifnull(`sub_part`.`comment`, ''))                                                                          AS `PARTITION_COMMENT`,
       if((`part`.`name` is null), '',
          internal_get_partition_nodegroup(if((`sub_part`.`name` is null), `part`.`options`,
                                              `sub_part`.`options`)))                                                AS `NODEGROUP`,
       ifnull(`sub_part_ts`.`name`, `part_ts`.`name`)                                                                AS `TABLESPACE_NAME`
from ((((((`mysql`.`tables` `tbl` join `mysql`.`schemata` `sch`
           on ((`sch`.`id` = `tbl`.`schema_id`))) join `mysql`.`catalogs` `cat`
          on ((`cat`.`id` = `sch`.`catalog_id`))) left join `mysql`.`table_partitions` `part`
         on ((`part`.`table_id` = `tbl`.`id`))) left join `mysql`.`table_partitions` `sub_part`
        on ((`sub_part`.`parent_partition_id` = `part`.`id`))) left join `mysql`.`tablespaces` `part_ts`
       on ((`part_ts`.`id` = `part`.`tablespace_id`))) left join `mysql`.`tablespaces` `sub_part_ts`
      on (((`sub_part`.`tablespace_id` is not null) and (`sub_part_ts`.`id` = `sub_part`.`tablespace_id`))))
where ((0 <> can_access_table(`sch`.`name`, `tbl`.`name`)) and (0 <> is_visible_dd_object(`tbl`.`hidden`)) and
       (`part`.`parent_partition_id` is null));

