create table performance_schema.events_stages_history_long
(
    THREAD_ID          bigint unsigned                                    not null,
    EVENT_ID           bigint unsigned                                    not null,
    END_EVENT_ID       bigint unsigned                                    null,
    EVENT_NAME         varchar(128)                                       not null,
    SOURCE             varchar(64)                                        null,
    TIMER_START        bigint unsigned                                    null,
    TIMER_END          bigint unsigned                                    null,
    TIMER_WAIT         bigint unsigned                                    null,
    WORK_COMPLETED     bigint unsigned                                    null,
    WORK_ESTIMATED     bigint unsigned                                    null,
    NESTING_EVENT_ID   bigint unsigned                                    null,
    NESTING_EVENT_TYPE enum ('TRANSACTION', 'STATEMENT', 'STAGE', 'WAIT') null
)
    engine = PERFORMANCE_SCHEMA;

