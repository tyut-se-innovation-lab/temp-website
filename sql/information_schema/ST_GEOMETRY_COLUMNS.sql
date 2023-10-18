create view information_schema.ST_GEOMETRY_COLUMNS as
select `information_schema`.`cols`.`TABLE_CATALOG` AS `TABLE_CATALOG`,
       `information_schema`.`cols`.`TABLE_SCHEMA`  AS `TABLE_SCHEMA`,
       `information_schema`.`cols`.`TABLE_NAME`    AS `TABLE_NAME`,
       `information_schema`.`cols`.`COLUMN_NAME`   AS `COLUMN_NAME`,
       `information_schema`.`srs`.`SRS_NAME`       AS `SRS_NAME`,
       `information_schema`.`cols`.`SRS_ID`        AS `SRS_ID`,
       `information_schema`.`cols`.`DATA_TYPE`     AS `GEOMETRY_TYPE_NAME`
from (`information_schema`.`COLUMNS` `cols` left join `information_schema`.`ST_SPATIAL_REFERENCE_SYSTEMS` `srs`
      on ((`information_schema`.`cols`.`SRS_ID` = `information_schema`.`srs`.`SRS_ID`)))
where (`information_schema`.`cols`.`DATA_TYPE` in
       ('geometry', 'point', 'linestring', 'polygon', 'multipoint', 'multilinestring', 'multipolygon',
        'geomcollection'));

