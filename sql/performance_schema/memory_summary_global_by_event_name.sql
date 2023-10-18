create table performance_schema.memory_summary_global_by_event_name
(
    EVENT_NAME                   varchar(128)    not null
        primary key using hash,
    COUNT_ALLOC                  bigint unsigned not null,
    COUNT_FREE                   bigint unsigned not null,
    SUM_NUMBER_OF_BYTES_ALLOC    bigint unsigned not null,
    SUM_NUMBER_OF_BYTES_FREE     bigint unsigned not null,
    LOW_COUNT_USED               bigint          not null,
    CURRENT_COUNT_USED           bigint          not null,
    HIGH_COUNT_USED              bigint          not null,
    LOW_NUMBER_OF_BYTES_USED     bigint          not null,
    CURRENT_NUMBER_OF_BYTES_USED bigint          not null,
    HIGH_NUMBER_OF_BYTES_USED    bigint          not null
)
    engine = PERFORMANCE_SCHEMA;

