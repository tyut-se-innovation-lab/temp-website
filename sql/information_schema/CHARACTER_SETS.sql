create view information_schema.CHARACTER_SETS as
select `cs`.`name`          AS `CHARACTER_SET_NAME`,
       `col`.`name`         AS `DEFAULT_COLLATE_NAME`,
       `cs`.`comment`       AS `DESCRIPTION`,
       `cs`.`mb_max_length` AS `MAXLEN`
from (`mysql`.`character_sets` `cs` join `mysql`.`collations` `col` on ((`cs`.`default_collation_id` = `col`.`id`)));

