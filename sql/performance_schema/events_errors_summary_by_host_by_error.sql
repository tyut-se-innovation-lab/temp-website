create table performance_schema.events_errors_summary_by_host_by_error
(
    HOST              char(255) charset ascii null,
    ERROR_NUMBER      int                     null,
    ERROR_NAME        varchar(64)             null,
    SQL_STATE         varchar(5)              null,
    SUM_ERROR_RAISED  bigint unsigned         not null,
    SUM_ERROR_HANDLED bigint unsigned         not null,
    FIRST_SEEN        timestamp               null,
    LAST_SEEN         timestamp               null,
    constraint HOST
        unique (HOST, ERROR_NUMBER) using hash
)
    engine = PERFORMANCE_SCHEMA;

