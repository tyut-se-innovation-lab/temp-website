create view information_schema.INNODB_DATAFILES as
select get_dd_tablespace_private_data(`ts`.`se_private_data`, 'id') AS `SPACE`, `ts_files`.`file_name` AS `PATH`
from (`mysql`.`tablespace_files` `ts_files` join `mysql`.`tablespaces` `ts`
      on ((`ts`.`id` = `ts_files`.`tablespace_id`)))
where ((`ts`.`se_private_data` is not null) and (`ts`.`engine` = 'InnoDB') and (`ts`.`name` <> 'mysql') and
       (`ts`.`name` <> 'innodb_temporary'));

