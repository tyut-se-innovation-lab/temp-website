create table performance_schema.events_stages_summary_by_account_by_event_name
(
    USER           char(32) collate utf8mb4_bin null,
    HOST           char(255) charset ascii      null,
    EVENT_NAME     varchar(128)                 not null,
    COUNT_STAR     bigint unsigned              not null,
    SUM_TIMER_WAIT bigint unsigned              not null,
    MIN_TIMER_WAIT bigint unsigned              not null,
    AVG_TIMER_WAIT bigint unsigned              not null,
    MAX_TIMER_WAIT bigint unsigned              not null,
    constraint ACCOUNT
        unique (USER, HOST, EVENT_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

