create view information_schema.ROLE_COLUMN_GRANTS as
with recursive `role_graph` (`c_parent_user`, `c_parent_host`, `c_from_user`, `c_from_host`, `c_to_user`, `c_to_host`,
                             `role_path`, `c_with_admin`, `c_enabled`)
                   as (select internal_get_username()                        AS `INTERNAL_GET_USERNAME()`,
                              internal_get_hostname()                        AS `INTERNAL_GET_HOSTNAME()`,
                              internal_get_username()                        AS `INTERNAL_GET_USERNAME()`,
                              internal_get_hostname()                        AS `INTERNAL_GET_HOSTNAME()`,
                              cast('' as char(64) charset utf8mb4)           AS `CAST('' as CHAR(64) CHARSET utf8mb4)`,
                              cast('' as char(255) charset utf8mb4)          AS `CAST('' as CHAR(255) CHARSET utf8mb4)`,
                              cast(sha2(concat(quote(internal_get_username()), '@', quote(internal_get_hostname())),
                                        256) as char(17000) charset utf8mb4) AS `CAST(SHA2(CONCAT(QUOTE(INTERNAL_GET_USERNAME()),'@',                        QUOTE(INTERNAL_GET_HOSTNAME())), 256)            AS CHAR(17000) CHARSET utf8mb4)`,
                              cast('N' as char(1) charset utf8mb4)           AS `CAST('N' as CHAR(1) CHARSET utf8mb4)`,
                              false                                          AS `FALSE`
                       union
                       select internal_get_username()                        AS `INTERNAL_GET_USERNAME()`,
                              internal_get_hostname()                        AS `INTERNAL_GET_HOSTNAME()`,
                              `mandatory_roles`.`ROLE_NAME`                  AS `ROLE_NAME`,
                              `mandatory_roles`.`ROLE_HOST`                  AS `ROLE_HOST`,
                              internal_get_username()                        AS `INTERNAL_GET_USERNAME()`,
                              internal_get_hostname()                        AS `INTERNAL_GET_HOSTNAME()`,
                              cast(sha2(concat(quote(`mandatory_roles`.`ROLE_NAME`), '@',
                                               convert(quote(`mandatory_roles`.`ROLE_HOST`) using utf8mb4)),
                                        256) as char(17000) charset utf8mb4) AS `CAST(SHA2(CONCAT(QUOTE(ROLE_NAME),'@',                   CONVERT(QUOTE(ROLE_HOST) using utf8mb4)), 256)              AS CHAR(17000) CHARSET utf8mb4)`,
                              cast('N' as char(1) charset utf8mb4)           AS `CAST('N' as CHAR(1) CHARSET utf8mb4)`,
                              false                                          AS `FALSE`
                       from json_table(internal_get_mandatory_roles_json(), '$[*]'
                                       columns (`ROLE_NAME` varchar(255) character set utf8mb4 path '$.ROLE_NAME', `ROLE_HOST` varchar(255) character set utf8mb4 path '$.ROLE_HOST')) `mandatory_roles`
                       where concat(quote(`mandatory_roles`.`ROLE_NAME`), '@',
                                    convert(quote(`mandatory_roles`.`ROLE_HOST`) using utf8mb4)) in
                             (select concat(convert(quote(`mysql`.`role_edges`.`FROM_USER`) using utf8mb4), '@',
                                            convert(quote(`mysql`.`role_edges`.`FROM_HOST`) using utf8mb4))
                              from `mysql`.`role_edges`
                              where ((`mysql`.`role_edges`.`TO_USER` = internal_get_username()) and
                                     (convert(`mysql`.`role_edges`.`TO_HOST` using utf8mb4) =
                                      convert(internal_get_hostname() using utf8mb4)))) is false
                       union
                       select `role_graph`.`c_parent_user`                                                       AS `c_parent_user`,
                              `role_graph`.`c_parent_host`                                                       AS `c_parent_host`,
                              `mysql`.`role_edges`.`FROM_USER`                                                   AS `FROM_USER`,
                              `mysql`.`role_edges`.`FROM_HOST`                                                   AS `FROM_HOST`,
                              `mysql`.`role_edges`.`TO_USER`                                                     AS `TO_USER`,
                              `mysql`.`role_edges`.`TO_HOST`                                                     AS `TO_HOST`,
                              if((locate(sha2(concat(convert(quote(`mysql`.`role_edges`.`FROM_USER`) using utf8mb4),
                                                     '@',
                                                     convert(quote(`mysql`.`role_edges`.`FROM_HOST`) using utf8mb4)),
                                              256), `role_graph`.`role_path`) = 0),
                                 concat(`role_graph`.`role_path`, '->', convert(sha2(concat(
                                                                                             convert(quote(`mysql`.`role_edges`.`FROM_USER`) using utf8mb4),
                                                                                             '@',
                                                                                             convert(quote(`mysql`.`role_edges`.`FROM_HOST`) using utf8mb4)),
                                                                                     256) using utf8mb4)),
                                 NULL)                                                                           AS `IF(LOCATE(SHA2(CONCAT(QUOTE(FROM_USER),'@',                      CONVERT(QUOTE(FROM_HOST) using utf8mb4)), 256),                 role_path) = 0,          CONCAT(role_path,'->', SHA2(CONCAT(QUOTE(FROM_USER),'@',           CONVERT(QUOTE(FROM_HOST) using utf8`,
                              `mysql`.`role_edges`.`WITH_ADMIN_OPTION`                                           AS `WITH_ADMIN_OPTION`,
                              if(((0 <> `role_graph`.`c_enabled`) or (0 <> internal_is_enabled_role(
                                      `mysql`.`role_edges`.`FROM_USER`, `mysql`.`role_edges`.`FROM_HOST`))), true,
                                 false)                                                                          AS `IF(c_enabled OR        INTERNAL_IS_ENABLED_ROLE(FROM_USER, FROM_HOST), TRUE, FALSE)`
                       from (`mysql`.`role_edges` join `role_graph`)
                       where ((`mysql`.`role_edges`.`TO_USER` = `role_graph`.`c_from_user`) and
                              (convert(`mysql`.`role_edges`.`TO_HOST` using utf8mb4) = `role_graph`.`c_from_host`) and
                              (`role_graph`.`role_path` is not null)))
select distinct internal_get_username(`tp`.`Grantor`)                          AS `GRANTOR`,
                internal_get_hostname(`tp`.`Grantor`)                          AS `GRANTOR_HOST`,
                `cp`.`User`                                                    AS `GRANTEE`,
                `cp`.`Host`                                                    AS `GRANTEE_HOST`,
                'def'                                                          AS `TABLE_CATALOG`,
                `cp`.`Db`                                                      AS `TABLE_SCHEMA`,
                `cp`.`Table_name`                                              AS `TABLE_NAME`,
                `cp`.`Column_name`                                             AS `COLUMN_NAME`,
                `cp`.`Column_priv`                                             AS `PRIVILEGE_TYPE`,
                if((find_in_set('Grant', `tp`.`Table_priv`) > 0), 'YES', 'NO') AS `IS_GRANTABLE`
from ((`mysql`.`tables_priv` `tp` join `role_graph` `rg` on (((`tp`.`User` = `rg`.`c_from_user`) and
                                                              (convert(`tp`.`Host` using utf8mb4) = `rg`.`c_from_host`)))) join `mysql`.`columns_priv` `cp`
      on (((convert(`tp`.`Host` using utf8mb4) = `cp`.`Host`) and (`cp`.`Db` = `tp`.`Db`) and
           (`cp`.`User` = `tp`.`User`) and (`cp`.`Table_name` = `tp`.`Table_name`))))
where ((`cp`.`Column_priv` > 0) and (`rg`.`c_to_user` <> '') and (`rg`.`c_enabled` = true));

