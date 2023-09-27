create table performance_schema.events_waits_summary_global_by_event_name
(
    EVENT_NAME     varchar(128)    not null
        primary key using hash,
    COUNT_STAR     bigint unsigned not null,
    SUM_TIMER_WAIT bigint unsigned not null,
    MIN_TIMER_WAIT bigint unsigned not null,
    AVG_TIMER_WAIT bigint unsigned not null,
    MAX_TIMER_WAIT bigint unsigned not null
)
    engine = PERFORMANCE_SCHEMA;

