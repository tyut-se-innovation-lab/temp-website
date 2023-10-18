create view information_schema.INNODB_FOREIGN_COLS as
select (concat(`sch`.`name`, '/', `fk`.`name`) collate utf8mb3_tolower_ci) AS `ID`,
       `col`.`name`                                                        AS `FOR_COL_NAME`,
       `fk_col`.`referenced_column_name`                                   AS `REF_COL_NAME`,
       `fk_col`.`ordinal_position`                                         AS `POS`
from ((((`mysql`.`foreign_key_column_usage` `fk_col` join `mysql`.`foreign_keys` `fk`
         on ((`fk`.`id` = `fk_col`.`foreign_key_id`))) join `mysql`.`tables` `tbl`
        on ((`fk`.`table_id` = `tbl`.`id`))) join `mysql`.`schemata` `sch`
       on ((`fk`.`schema_id` = `sch`.`id`))) join `mysql`.`columns` `col`
      on (((`tbl`.`id` = `col`.`table_id`) and (`fk_col`.`column_id` = `col`.`id`))))
where ((`tbl`.`type` <> 'VIEW') and (`tbl`.`hidden` = 'Visible') and (`tbl`.`se_private_id` is not null) and
       (`tbl`.`engine` = 'INNODB'));

