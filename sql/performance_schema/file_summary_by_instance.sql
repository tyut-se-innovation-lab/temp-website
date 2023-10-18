create table performance_schema.file_summary_by_instance
(
    FILE_NAME                 varchar(512)    not null,
    EVENT_NAME                varchar(128)    not null,
    OBJECT_INSTANCE_BEGIN     bigint unsigned not null
        primary key using hash,
    COUNT_STAR                bigint unsigned not null,
    SUM_TIMER_WAIT            bigint unsigned not null,
    MIN_TIMER_WAIT            bigint unsigned not null,
    AVG_TIMER_WAIT            bigint unsigned not null,
    MAX_TIMER_WAIT            bigint unsigned not null,
    COUNT_READ                bigint unsigned not null,
    SUM_TIMER_READ            bigint unsigned not null,
    MIN_TIMER_READ            bigint unsigned not null,
    AVG_TIMER_READ            bigint unsigned not null,
    MAX_TIMER_READ            bigint unsigned not null,
    SUM_NUMBER_OF_BYTES_READ  bigint          not null,
    COUNT_WRITE               bigint unsigned not null,
    SUM_TIMER_WRITE           bigint unsigned not null,
    MIN_TIMER_WRITE           bigint unsigned not null,
    AVG_TIMER_WRITE           bigint unsigned not null,
    MAX_TIMER_WRITE           bigint unsigned not null,
    SUM_NUMBER_OF_BYTES_WRITE bigint          not null,
    COUNT_MISC                bigint unsigned not null,
    SUM_TIMER_MISC            bigint unsigned not null,
    MIN_TIMER_MISC            bigint unsigned not null,
    AVG_TIMER_MISC            bigint unsigned not null,
    MAX_TIMER_MISC            bigint unsigned not null
)
    engine = PERFORMANCE_SCHEMA;

create index EVENT_NAME
    on performance_schema.file_summary_by_instance (EVENT_NAME)
    using hash;

create index FILE_NAME
    on performance_schema.file_summary_by_instance (FILE_NAME)
    using hash;

