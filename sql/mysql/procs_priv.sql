create table mysql.procs_priv
(
    Host         char(255) charset ascii                                   default ''                not null,
    Db           char(64)                                                  default ''                not null,
    User         char(32)                                                  default ''                not null,
    Routine_name char(64) charset utf8mb3                                  default ''                not null,
    Routine_type enum ('FUNCTION', 'PROCEDURE')                                                      not null,
    Grantor      varchar(288)                                              default ''                not null,
    Proc_priv    set ('Execute', 'Alter Routine', 'Grant') charset utf8mb3 default ''                not null,
    Timestamp    timestamp                                                 default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    primary key (Host, User, Db, Routine_name, Routine_type)
)
    comment 'Procedure privileges' collate = utf8mb3_bin;

create index Grantor
    on mysql.procs_priv (Grantor);

