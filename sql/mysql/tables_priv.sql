create table mysql.tables_priv
(
    Host        char(255) charset ascii                                                                                                                                        default ''                not null,
    Db          char(64)                                                                                                                                                       default ''                not null,
    User        char(32)                                                                                                                                                       default ''                not null,
    Table_name  char(64)                                                                                                                                                       default ''                not null,
    Grantor     varchar(288)                                                                                                                                                   default ''                not null,
    Timestamp   timestamp                                                                                                                                                      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    Table_priv  set ('Select', 'Insert', 'Update', 'Delete', 'Create', 'Drop', 'Grant', 'References', 'Index', 'Alter', 'Create View', 'Show view', 'Trigger') charset utf8mb3 default ''                not null,
    Column_priv set ('Select', 'Insert', 'Update', 'References') charset utf8mb3                                                                                               default ''                not null,
    primary key (Host, User, Db, Table_name)
)
    comment 'Table privileges' collate = utf8mb3_bin;

create index Grantor
    on mysql.tables_priv (Grantor);

