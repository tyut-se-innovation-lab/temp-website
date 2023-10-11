create table performance_schema.objects_summary_global_by_type
(
    OBJECT_TYPE    varchar(64)     null,
    OBJECT_SCHEMA  varchar(64)     null,
    OBJECT_NAME    varchar(64)     null,
    COUNT_STAR     bigint unsigned not null,
    SUM_TIMER_WAIT bigint unsigned not null,
    MIN_TIMER_WAIT bigint unsigned not null,
    AVG_TIMER_WAIT bigint unsigned not null,
    MAX_TIMER_WAIT bigint unsigned not null,
    constraint OBJECT
        unique (OBJECT_TYPE, OBJECT_SCHEMA, OBJECT_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

