create view information_schema.TABLESPACES_EXTENSIONS as
select `tsps`.`name` AS `TABLESPACE_NAME`, `tsps`.`engine_attribute` AS `ENGINE_ATTRIBUTE`
from `mysql`.`tablespaces` `tsps`;

