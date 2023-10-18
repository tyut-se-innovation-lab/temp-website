create table mysql.func
(
    name char(64)  default ''                           not null
        primary key,
    ret  tinyint   default 0                            not null,
    dl   char(128) default ''                           not null,
    type enum ('function', 'aggregate') charset utf8mb3 not null
)
    comment 'User defined functions' collate = utf8mb3_bin;

