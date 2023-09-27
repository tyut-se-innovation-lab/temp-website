create view information_schema.EVENTS as
select (`cat`.`name` collate utf8mb3_tolower_ci)                                         AS `EVENT_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci)                                         AS `EVENT_SCHEMA`,
       `evt`.`name`                                                                      AS `EVENT_NAME`,
       `evt`.`definer`                                                                   AS `DEFINER`,
       `evt`.`time_zone`                                                                 AS `TIME_ZONE`,
       'SQL'                                                                             AS `EVENT_BODY`,
       `evt`.`definition_utf8`                                                           AS `EVENT_DEFINITION`,
       if((`evt`.`interval_value` is null), 'ONE TIME', 'RECURRING')                     AS `EVENT_TYPE`,
       convert_tz(`evt`.`execute_at`, '+00:00', `evt`.`time_zone`)                       AS `EXECUTE_AT`,
       convert_interval_to_user_interval(`evt`.`interval_value`, `evt`.`interval_field`) AS `INTERVAL_VALUE`,
       `evt`.`interval_field`                                                            AS `INTERVAL_FIELD`,
       `evt`.`sql_mode`                                                                  AS `SQL_MODE`,
       convert_tz(`evt`.`starts`, '+00:00', `evt`.`time_zone`)                           AS `STARTS`,
       convert_tz(`evt`.`ends`, '+00:00', `evt`.`time_zone`)                             AS `ENDS`,
       `evt`.`status`                                                                    AS `STATUS`,
       if((`evt`.`on_completion` = 'DROP'), 'NOT PRESERVE', 'PRESERVE')                  AS `ON_COMPLETION`,
       `evt`.`created`                                                                   AS `CREATED`,
       `evt`.`last_altered`                                                              AS `LAST_ALTERED`,
       convert_tz(`evt`.`last_executed`, '+00:00', `evt`.`time_zone`)                    AS `LAST_EXECUTED`,
       `evt`.`comment`                                                                   AS `EVENT_COMMENT`,
       `evt`.`originator`                                                                AS `ORIGINATOR`,
       `cs_client`.`name`                                                                AS `CHARACTER_SET_CLIENT`,
       `coll_conn`.`name`                                                                AS `COLLATION_CONNECTION`,
       `coll_db`.`name`                                                                  AS `DATABASE_COLLATION`
from ((((((`mysql`.`events` `evt` join `mysql`.`schemata` `sch`
           on ((`evt`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
          on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`collations` `coll_client`
         on ((`coll_client`.`id` = `evt`.`client_collation_id`))) join `mysql`.`character_sets` `cs_client`
        on ((`cs_client`.`id` = `coll_client`.`character_set_id`))) join `mysql`.`collations` `coll_conn`
       on ((`coll_conn`.`id` = `evt`.`connection_collation_id`))) join `mysql`.`collations` `coll_db`
      on ((`coll_db`.`id` = `evt`.`schema_collation_id`)))
where (0 <> can_access_event(`sch`.`name`));

