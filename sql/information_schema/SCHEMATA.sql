create view information_schema.SCHEMATA as
select (`cat`.`name` collate utf8mb3_tolower_ci) AS `CATALOG_NAME`,
       (`sch`.`name` collate utf8mb3_tolower_ci) AS `SCHEMA_NAME`,
       `cs`.`name`                               AS `DEFAULT_CHARACTER_SET_NAME`,
       `col`.`name`                              AS `DEFAULT_COLLATION_NAME`,
       NULL                                      AS `SQL_PATH`,
       `sch`.`default_encryption`                AS `DEFAULT_ENCRYPTION`
from (((`mysql`.`schemata` `sch` join `mysql`.`catalogs` `cat`
        on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`collations` `col`
       on ((`sch`.`default_collation_id` = `col`.`id`))) join `mysql`.`character_sets` `cs`
      on ((`col`.`character_set_id` = `cs`.`id`)))
where (0 <> can_access_database(`sch`.`name`));

