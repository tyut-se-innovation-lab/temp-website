create table performance_schema.events_transactions_summary_global_by_event_name
(
    EVENT_NAME           varchar(128)    not null
        primary key using hash,
    COUNT_STAR           bigint unsigned not null,
    SUM_TIMER_WAIT       bigint unsigned not null,
    MIN_TIMER_WAIT       bigint unsigned not null,
    AVG_TIMER_WAIT       bigint unsigned not null,
    MAX_TIMER_WAIT       bigint unsigned not null,
    COUNT_READ_WRITE     bigint unsigned not null,
    SUM_TIMER_READ_WRITE bigint unsigned not null,
    MIN_TIMER_READ_WRITE bigint unsigned not null,
    AVG_TIMER_READ_WRITE bigint unsigned not null,
    MAX_TIMER_READ_WRITE bigint unsigned not null,
    COUNT_READ_ONLY      bigint unsigned not null,
    SUM_TIMER_READ_ONLY  bigint unsigned not null,
    MIN_TIMER_READ_ONLY  bigint unsigned not null,
    AVG_TIMER_READ_ONLY  bigint unsigned not null,
    MAX_TIMER_READ_ONLY  bigint unsigned not null
)
    engine = PERFORMANCE_SCHEMA;

