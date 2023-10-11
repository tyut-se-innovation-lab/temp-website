create table performance_schema.error_log
(
    LOGGED     timestamp(6)                                not null
        primary key using hash,
    THREAD_ID  bigint unsigned                             null,
    PRIO       enum ('System', 'Error', 'Warning', 'Note') not null,
    ERROR_CODE varchar(10)                                 null,
    SUBSYSTEM  varchar(7)                                  null,
    DATA       text                                        not null
)
    engine = PERFORMANCE_SCHEMA;

create index ERROR_CODE
    on performance_schema.error_log (ERROR_CODE)
    using hash;

create index PRIO
    on performance_schema.error_log (PRIO)
    using hash;

create index SUBSYSTEM
    on performance_schema.error_log (SUBSYSTEM)
    using hash;

create index THREAD_ID
    on performance_schema.error_log (THREAD_ID)
    using hash;

