create view information_schema.ENABLED_ROLES as
select `current_user_enabled_roles`.`ROLE_NAME`                                              AS `ROLE_NAME`,
       `current_user_enabled_roles`.`ROLE_HOST`                                              AS `ROLE_HOST`,
       (select if(count(0), 'YES', 'NO')
        from `mysql`.`default_roles`
        where ((`mysql`.`default_roles`.`DEFAULT_ROLE_USER` = `current_user_enabled_roles`.`ROLE_NAME`) and
               (convert(`mysql`.`default_roles`.`DEFAULT_ROLE_HOST` using utf8mb4) =
                `current_user_enabled_roles`.`ROLE_HOST`) and
               (`mysql`.`default_roles`.`USER` = internal_get_username()) and
               (convert(`mysql`.`default_roles`.`HOST` using utf8mb4) =
                convert(internal_get_hostname() using utf8mb4))))                            AS `IS_DEFAULT`,
       if(internal_is_mandatory_role(`current_user_enabled_roles`.`ROLE_NAME`,
                                     `current_user_enabled_roles`.`ROLE_HOST`), 'YES', 'NO') AS `IS_MANDATORY`
from json_table(internal_get_enabled_role_json(), '$[*]'
                columns (`ROLE_NAME` varchar(255) character set utf8mb4 path '$.ROLE_NAME', `ROLE_HOST` varchar(255) character set utf8mb4 path '$.ROLE_HOST')) `current_user_enabled_roles`;

