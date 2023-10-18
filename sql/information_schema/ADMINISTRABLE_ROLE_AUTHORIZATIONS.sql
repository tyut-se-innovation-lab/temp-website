create view information_schema.ADMINISTRABLE_ROLE_AUTHORIZATIONS as
select `information_schema`.`applicable_roles`.`USER`         AS `USER`,
       `information_schema`.`applicable_roles`.`HOST`         AS `HOST`,
       `information_schema`.`applicable_roles`.`GRANTEE`      AS `GRANTEE`,
       `information_schema`.`applicable_roles`.`GRANTEE_HOST` AS `GRANTEE_HOST`,
       `information_schema`.`applicable_roles`.`ROLE_NAME`    AS `ROLE_NAME`,
       `information_schema`.`applicable_roles`.`ROLE_HOST`    AS `ROLE_HOST`,
       `information_schema`.`applicable_roles`.`IS_GRANTABLE` AS `IS_GRANTABLE`,
       `information_schema`.`applicable_roles`.`IS_DEFAULT`   AS `IS_DEFAULT`,
       `information_schema`.`applicable_roles`.`IS_MANDATORY` AS `IS_MANDATORY`
from `information_schema`.`APPLICABLE_ROLES`
where (`information_schema`.`applicable_roles`.`IS_GRANTABLE` = 'YES');

