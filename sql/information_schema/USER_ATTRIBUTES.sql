create view information_schema.USER_ATTRIBUTES as
select `mysql`.`user`.`User`                                                      AS `USER`,
       `mysql`.`user`.`Host`                                                      AS `HOST`,
       json_unquote(json_extract(`mysql`.`user`.`User_attributes`, '$.metadata')) AS `ATTRIBUTE`
from `mysql`.`user`
where (0 <> can_access_user(`mysql`.`user`.`User`, `mysql`.`user`.`Host`));

