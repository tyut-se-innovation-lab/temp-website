create view information_schema.RESOURCE_GROUPS as
select `res`.`resource_group_name`              AS `RESOURCE_GROUP_NAME`,
       `res`.`resource_group_type`              AS `RESOURCE_GROUP_TYPE`,
       `res`.`resource_group_enabled`           AS `RESOURCE_GROUP_ENABLED`,
       convert_cpu_id_mask(`res`.`cpu_id_mask`) AS `VCPU_IDS`,
       `res`.`thread_priority`                  AS `THREAD_PRIORITY`
from `mysql`.`resource_groups` `res`
where (0 <> can_access_resource_group(`res`.`resource_group_name`));

