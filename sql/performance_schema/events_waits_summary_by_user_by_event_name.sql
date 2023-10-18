create table performance_schema.events_waits_summary_by_user_by_event_name
(
    USER           char(32) collate utf8mb4_bin null,
    EVENT_NAME     varchar(128)                 not null,
    COUNT_STAR     bigint unsigned              not null,
    SUM_TIMER_WAIT bigint unsigned              not null,
    MIN_TIMER_WAIT bigint unsigned              not null,
    AVG_TIMER_WAIT bigint unsigned              not null,
    MAX_TIMER_WAIT bigint unsigned              not null,
    constraint USER
        unique (USER, EVENT_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

