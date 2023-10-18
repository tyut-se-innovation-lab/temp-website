create table mysql.slow_log
(
    start_time     timestamp(6) default CURRENT_TIMESTAMP(6) not null on update CURRENT_TIMESTAMP(6),
    user_host      mediumtext                                not null,
    query_time     time(6)                                   not null,
    lock_time      time(6)                                   not null,
    rows_sent      int                                       not null,
    rows_examined  int                                       not null,
    db             varchar(512)                              not null,
    last_insert_id int                                       not null,
    insert_id      int                                       not null,
    server_id      int unsigned                              not null,
    sql_text       mediumblob                                not null,
    thread_id      bigint unsigned                           not null
)
    comment 'Slow log' engine = CSV
                       charset = utf8mb3;

