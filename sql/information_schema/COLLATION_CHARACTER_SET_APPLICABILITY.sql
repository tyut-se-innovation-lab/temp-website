create view information_schema.COLLATION_CHARACTER_SET_APPLICABILITY as
select `col`.`name` AS `COLLATION_NAME`, `cs`.`name` AS `CHARACTER_SET_NAME`
from (`mysql`.`character_sets` `cs` join `mysql`.`collations` `col` on ((`cs`.`id` = `col`.`character_set_id`)));

