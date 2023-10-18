create table performance_schema.memory_summary_by_thread_by_event_name
(
    THREAD_ID                    bigint unsigned not null,
    EVENT_NAME                   varchar(128)    not null,
    COUNT_ALLOC                  bigint unsigned not null,
    COUNT_FREE                   bigint unsigned not null,
    SUM_NUMBER_OF_BYTES_ALLOC    bigint unsigned not null,
    SUM_NUMBER_OF_BYTES_FREE     bigint unsigned not null,
    LOW_COUNT_USED               bigint          not null,
    CURRENT_COUNT_USED           bigint          not null,
    HIGH_COUNT_USED              bigint          not null,
    LOW_NUMBER_OF_BYTES_USED     bigint          not null,
    CURRENT_NUMBER_OF_BYTES_USED bigint          not null,
    HIGH_NUMBER_OF_BYTES_USED    bigint          not null,
    primary key (THREAD_ID, EVENT_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

