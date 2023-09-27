create table performance_schema.memory_summary_by_user_by_event_name
(
    USER                         char(32) collate utf8mb4_bin null,
    EVENT_NAME                   varchar(128)                 not null,
    COUNT_ALLOC                  bigint unsigned              not null,
    COUNT_FREE                   bigint unsigned              not null,
    SUM_NUMBER_OF_BYTES_ALLOC    bigint unsigned              not null,
    SUM_NUMBER_OF_BYTES_FREE     bigint unsigned              not null,
    LOW_COUNT_USED               bigint                       not null,
    CURRENT_COUNT_USED           bigint                       not null,
    HIGH_COUNT_USED              bigint                       not null,
    LOW_NUMBER_OF_BYTES_USED     bigint                       not null,
    CURRENT_NUMBER_OF_BYTES_USED bigint                       not null,
    HIGH_NUMBER_OF_BYTES_USED    bigint                       not null,
    constraint USER
        unique (USER, EVENT_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

