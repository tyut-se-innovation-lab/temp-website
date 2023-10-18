create table mysql.innodb_index_stats
(
    database_name    varchar(64)                         not null,
    table_name       varchar(199)                        not null,
    index_name       varchar(64)                         not null,
    last_update      timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    stat_name        varchar(64)                         not null,
    stat_value       bigint unsigned                     not null,
    sample_size      bigint unsigned                     null,
    stat_description varchar(1024)                       not null,
    primary key (database_name, table_name, index_name, stat_name)
)
    collate = utf8mb3_bin;

