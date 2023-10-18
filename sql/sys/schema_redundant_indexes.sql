create definer = `mysql.sys`@localhost view sys.schema_redundant_indexes as
select `sys`.`redundant_keys`.`table_schema`                                                                       AS `table_schema`,
       `sys`.`redundant_keys`.`table_name`                                                                         AS `table_name`,
       `sys`.`redundant_keys`.`index_name`                                                                         AS `redundant_index_name`,
       `sys`.`redundant_keys`.`index_columns`                                                                      AS `redundant_index_columns`,
       `sys`.`redundant_keys`.`non_unique`                                                                         AS `redundant_index_non_unique`,
       `sys`.`dominant_keys`.`index_name`                                                                          AS `dominant_index_name`,
       `sys`.`dominant_keys`.`index_columns`                                                                       AS `dominant_index_columns`,
       `sys`.`dominant_keys`.`non_unique`                                                                          AS `dominant_index_non_unique`,
       if(((0 <> `sys`.`redundant_keys`.`subpart_exists`) or (0 <> `sys`.`dominant_keys`.`subpart_exists`)), 1,
          0)                                                                                                       AS `subpart_exists`,
       concat('ALTER TABLE `', `sys`.`redundant_keys`.`table_schema`, '`.`', `sys`.`redundant_keys`.`table_name`,
              '` DROP INDEX `', `sys`.`redundant_keys`.`index_name`,
              '`')                                                                                                 AS `sql_drop_index`
from (`sys`.`x$schema_flattened_keys` `redundant_keys` join `sys`.`x$schema_flattened_keys` `dominant_keys`
      on (((`sys`.`redundant_keys`.`table_schema` = `sys`.`dominant_keys`.`table_schema`) and
           (`sys`.`redundant_keys`.`table_name` = `sys`.`dominant_keys`.`table_name`))))
where ((`sys`.`redundant_keys`.`index_name` <> `sys`.`dominant_keys`.`index_name`) and
       (((`sys`.`redundant_keys`.`index_columns` = `sys`.`dominant_keys`.`index_columns`) and
         ((`sys`.`redundant_keys`.`non_unique` > `sys`.`dominant_keys`.`non_unique`) or
          ((`sys`.`redundant_keys`.`non_unique` = `sys`.`dominant_keys`.`non_unique`) and
           (if((`sys`.`redundant_keys`.`index_name` = 'PRIMARY'), '', `sys`.`redundant_keys`.`index_name`) >
            if((`sys`.`dominant_keys`.`index_name` = 'PRIMARY'), '', `sys`.`dominant_keys`.`index_name`))))) or
        ((locate(concat(`sys`.`redundant_keys`.`index_columns`, ','), `sys`.`dominant_keys`.`index_columns`) = 1) and
         (`sys`.`redundant_keys`.`non_unique` = 1)) or
        ((locate(concat(`sys`.`dominant_keys`.`index_columns`, ','), `sys`.`redundant_keys`.`index_columns`) = 1) and
         (`sys`.`dominant_keys`.`non_unique` = 0))));

