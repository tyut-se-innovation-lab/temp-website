create view information_schema.INNODB_TABLESPACES_BRIEF as
select get_dd_tablespace_private_data(`ts`.`se_private_data`, 'id')    AS `SPACE`,
       `ts`.`name`                                                     AS `NAME`,
       `ts_files`.`file_name`                                          AS `PATH`,
       get_dd_tablespace_private_data(`ts`.`se_private_data`, 'flags') AS `FLAG`,
       if((get_dd_tablespace_private_data(`ts`.`se_private_data`, 'id') = 0), 'System',
          if((((get_dd_tablespace_private_data(`ts`.`se_private_data`, 'flags') & 2048) >> 11) <> 0), 'General',
             'Single'))                                                AS `SPACE_TYPE`
from (`mysql`.`tablespace_files` `ts_files` join `mysql`.`tablespaces` `ts`
      on ((`ts`.`id` = `ts_files`.`tablespace_id`)))
where ((`ts`.`se_private_data` is not null) and (`ts`.`engine` = 'InnoDB') and (`ts`.`name` <> 'mysql') and
       (`ts`.`name` <> 'innodb_temporary'));

