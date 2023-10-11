create view information_schema.TRIGGERS as
select (`cat`.`name` collate utf8mb3_tolower_ci) AS `TRIGGER_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci) AS `TRIGGER_SCHEMA`,
       `trg`.`name`                              AS `TRIGGER_NAME`,
       `trg`.`event_type`                        AS `EVENT_MANIPULATION`,
       (`cat`.`name` collate utf8mb3_tolower_ci) AS `EVENT_OBJECT_CATALOG`,
       (`sch`.`name` collate utf8mb3_tolower_ci) AS `EVENT_OBJECT_SCHEMA`,
       (`tbl`.`name` collate utf8mb3_tolower_ci) AS `EVENT_OBJECT_TABLE`,
       `trg`.`action_order`                      AS `ACTION_ORDER`,
       NULL                                      AS `ACTION_CONDITION`,
       `trg`.`action_statement_utf8`             AS `ACTION_STATEMENT`,
       'ROW'                                     AS `ACTION_ORIENTATION`,
       `trg`.`action_timing`                     AS `ACTION_TIMING`,
       NULL                                      AS `ACTION_REFERENCE_OLD_TABLE`,
       NULL                                      AS `ACTION_REFERENCE_NEW_TABLE`,
       'OLD'                                     AS `ACTION_REFERENCE_OLD_ROW`,
       'NEW'                                     AS `ACTION_REFERENCE_NEW_ROW`,
       `trg`.`created`                           AS `CREATED`,
       `trg`.`sql_mode`                          AS `SQL_MODE`,
       `trg`.`definer`                           AS `DEFINER`,
       `cs_client`.`name`                        AS `CHARACTER_SET_CLIENT`,
       `coll_conn`.`name`                        AS `COLLATION_CONNECTION`,
       `coll_db`.`name`                          AS `DATABASE_COLLATION`
from (((((((`mysql`.`triggers` `trg` join `mysql`.`tables` `tbl`
            on ((`tbl`.`id` = `trg`.`table_id`))) join `mysql`.`schemata` `sch`
           on ((`tbl`.`schema_id` = `sch`.`id`))) join `mysql`.`catalogs` `cat`
          on ((`cat`.`id` = `sch`.`catalog_id`))) join `mysql`.`collations` `coll_client`
         on ((`coll_client`.`id` = `trg`.`client_collation_id`))) join `mysql`.`character_sets` `cs_client`
        on ((`cs_client`.`id` = `coll_client`.`character_set_id`))) join `mysql`.`collations` `coll_conn`
       on ((`coll_conn`.`id` = `trg`.`connection_collation_id`))) join `mysql`.`collations` `coll_db`
      on ((`coll_db`.`id` = `trg`.`schema_collation_id`)))
where ((`tbl`.`type` <> 'VIEW') and (0 <> can_access_trigger(`sch`.`name`, `tbl`.`name`)) and
       (0 <> is_visible_dd_object(`tbl`.`hidden`)));

