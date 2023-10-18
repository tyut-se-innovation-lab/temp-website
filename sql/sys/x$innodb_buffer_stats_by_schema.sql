create definer = `mysql.sys`@localhost view sys.x$innodb_buffer_stats_by_schema as
select if((locate('.', `ibp`.`TABLE_NAME`) = 0), 'InnoDB System',
          replace(substring_index(`ibp`.`TABLE_NAME`, '.', 1), '`', ''))                                  AS `object_schema`,
       sum(if((`ibp`.`COMPRESSED_SIZE` = 0), 16384, `ibp`.`COMPRESSED_SIZE`))                             AS `allocated`,
       sum(`ibp`.`DATA_SIZE`)                                                                             AS `data`,
       count(`ibp`.`PAGE_NUMBER`)                                                                         AS `pages`,
       count(if((`ibp`.`IS_HASHED` = 'YES'), 1, NULL))                                                    AS `pages_hashed`,
       count(if((`ibp`.`IS_OLD` = 'YES'), 1, NULL))                                                       AS `pages_old`,
       round(ifnull((sum(`ibp`.`NUMBER_RECORDS`) / nullif(count(distinct `ibp`.`INDEX_NAME`), 0)), 0),
             0)                                                                                           AS `rows_cached`
from `information_schema`.`INNODB_BUFFER_PAGE` `ibp`
where (`ibp`.`TABLE_NAME` is not null)
group by `object_schema`
order by sum(if((`ibp`.`COMPRESSED_SIZE` = 0), 16384, `ibp`.`COMPRESSED_SIZE`)) desc;

