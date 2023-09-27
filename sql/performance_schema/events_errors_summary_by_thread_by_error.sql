create table performance_schema.events_errors_summary_by_thread_by_error
(
    THREAD_ID         bigint unsigned not null,
    ERROR_NUMBER      int             null,
    ERROR_NAME        varchar(64)     null,
    SQL_STATE         varchar(5)      null,
    SUM_ERROR_RAISED  bigint unsigned not null,
    SUM_ERROR_HANDLED bigint unsigned not null,
    FIRST_SEEN        timestamp       null,
    LAST_SEEN         timestamp       null,
    constraint THREAD_ID
        unique (THREAD_ID, ERROR_NUMBER) using hash
)
    engine = PERFORMANCE_SCHEMA;

