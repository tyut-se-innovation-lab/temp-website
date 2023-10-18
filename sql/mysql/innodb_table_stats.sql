create table mysql.innodb_table_stats
(
    database_name            varchar(64)                         not null,
    table_name               varchar(199)                        not null,
    last_update              timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    n_rows                   bigint unsigned                     not null,
    clustered_index_size     bigint unsigned                     not null,
    sum_of_other_index_sizes bigint unsigned                     not null,
    primary key (database_name, table_name)
)
    collate = utf8mb3_bin;

