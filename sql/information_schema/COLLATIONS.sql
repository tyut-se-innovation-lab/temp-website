create view information_schema.COLLATIONS as
select `col`.`name`                                                                                AS `COLLATION_NAME`,
       `cs`.`name`                                                                                 AS `CHARACTER_SET_NAME`,
       `col`.`id`                                                                                  AS `ID`,
       if(exists(select 1
                 from `mysql`.`character_sets`
                 where (`mysql`.`character_sets`.`default_collation_id` = `col`.`id`)), 'Yes', '') AS `IS_DEFAULT`,
       if(`col`.`is_compiled`, 'Yes', '')                                                          AS `IS_COMPILED`,
       `col`.`sort_length`                                                                         AS `SORTLEN`,
       `col`.`pad_attribute`                                                                       AS `PAD_ATTRIBUTE`
from (`mysql`.`collations` `col` join `mysql`.`character_sets` `cs` on ((`col`.`character_set_id` = `cs`.`id`)));

