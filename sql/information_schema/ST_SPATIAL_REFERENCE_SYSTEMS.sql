create view information_schema.ST_SPATIAL_REFERENCE_SYSTEMS as
select `mysql`.`st_spatial_reference_systems`.`name`                     AS `SRS_NAME`,
       `mysql`.`st_spatial_reference_systems`.`id`                       AS `SRS_ID`,
       `mysql`.`st_spatial_reference_systems`.`organization`             AS `ORGANIZATION`,
       `mysql`.`st_spatial_reference_systems`.`organization_coordsys_id` AS `ORGANIZATION_COORDSYS_ID`,
       `mysql`.`st_spatial_reference_systems`.`definition`               AS `DEFINITION`,
       `mysql`.`st_spatial_reference_systems`.`description`              AS `DESCRIPTION`
from `mysql`.`st_spatial_reference_systems`;

