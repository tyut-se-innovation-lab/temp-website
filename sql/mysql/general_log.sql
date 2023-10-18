create table mysql.general_log
(
    event_time   timestamp(6) default CURRENT_TIMESTAMP(6) not null on update CURRENT_TIMESTAMP(6),
    user_host    mediumtext                                not null,
    thread_id    bigint unsigned                           not null,
    server_id    int unsigned                              not null,
    command_type varchar(64)                               not null,
    argument     mediumblob                                not null
)
    comment 'General log' engine = CSV
                          charset = utf8mb3;

