create view information_schema.INNODB_FIELDS as
select get_dd_index_private_data(`idx`.`se_private_data`, 'id') AS `INDEX_ID`,
       `col`.`name`                                             AS `NAME`,
       (`fld`.`ordinal_position` - 1)                           AS `POS`
from (((`mysql`.`index_column_usage` `fld` join `mysql`.`columns` `col`
        on ((`fld`.`column_id` = `col`.`id`))) join `mysql`.`indexes` `idx`
       on ((`fld`.`index_id` = `idx`.`id`))) join `mysql`.`tables` `tbl` on ((`tbl`.`id` = `idx`.`table_id`)))
where ((`tbl`.`type` <> 'VIEW') and (`tbl`.`hidden` = 'Visible') and (0 = `fld`.`hidden`) and
       (`tbl`.`se_private_id` is not null) and (`tbl`.`engine` = 'INNODB'));

