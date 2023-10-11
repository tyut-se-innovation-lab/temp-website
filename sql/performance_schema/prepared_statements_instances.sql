create table performance_schema.prepared_statements_instances
(
    OBJECT_INSTANCE_BEGIN       bigint unsigned                                             not null
        primary key using hash,
    STATEMENT_ID                bigint unsigned                                             not null,
    STATEMENT_NAME              varchar(64)                                                 null,
    SQL_TEXT                    longtext                                                    not null,
    OWNER_THREAD_ID             bigint unsigned                                             not null,
    OWNER_EVENT_ID              bigint unsigned                                             not null,
    OWNER_OBJECT_TYPE           enum ('EVENT', 'FUNCTION', 'PROCEDURE', 'TABLE', 'TRIGGER') null,
    OWNER_OBJECT_SCHEMA         varchar(64)                                                 null,
    OWNER_OBJECT_NAME           varchar(64)                                                 null,
    EXECUTION_ENGINE            enum ('PRIMARY', 'SECONDARY')                               null,
    TIMER_PREPARE               bigint unsigned                                             not null,
    COUNT_REPREPARE             bigint unsigned                                             not null,
    COUNT_EXECUTE               bigint unsigned                                             not null,
    SUM_TIMER_EXECUTE           bigint unsigned                                             not null,
    MIN_TIMER_EXECUTE           bigint unsigned                                             not null,
    AVG_TIMER_EXECUTE           bigint unsigned                                             not null,
    MAX_TIMER_EXECUTE           bigint unsigned                                             not null,
    SUM_LOCK_TIME               bigint unsigned                                             not null,
    SUM_ERRORS                  bigint unsigned                                             not null,
    SUM_WARNINGS                bigint unsigned                                             not null,
    SUM_ROWS_AFFECTED           bigint unsigned                                             not null,
    SUM_ROWS_SENT               bigint unsigned                                             not null,
    SUM_ROWS_EXAMINED           bigint unsigned                                             not null,
    SUM_CREATED_TMP_DISK_TABLES bigint unsigned                                             not null,
    SUM_CREATED_TMP_TABLES      bigint unsigned                                             not null,
    SUM_SELECT_FULL_JOIN        bigint unsigned                                             not null,
    SUM_SELECT_FULL_RANGE_JOIN  bigint unsigned                                             not null,
    SUM_SELECT_RANGE            bigint unsigned                                             not null,
    SUM_SELECT_RANGE_CHECK      bigint unsigned                                             not null,
    SUM_SELECT_SCAN             bigint unsigned                                             not null,
    SUM_SORT_MERGE_PASSES       bigint unsigned                                             not null,
    SUM_SORT_RANGE              bigint unsigned                                             not null,
    SUM_SORT_ROWS               bigint unsigned                                             not null,
    SUM_SORT_SCAN               bigint unsigned                                             not null,
    SUM_NO_INDEX_USED           bigint unsigned                                             not null,
    SUM_NO_GOOD_INDEX_USED      bigint unsigned                                             not null,
    SUM_CPU_TIME                bigint unsigned                                             not null,
    MAX_CONTROLLED_MEMORY       bigint unsigned                                             not null,
    MAX_TOTAL_MEMORY            bigint unsigned                                             not null,
    COUNT_SECONDARY             bigint unsigned                                             not null,
    constraint OWNER_THREAD_ID
        unique (OWNER_THREAD_ID, OWNER_EVENT_ID) using hash
)
    engine = PERFORMANCE_SCHEMA;

create index OWNER_OBJECT_TYPE
    on performance_schema.prepared_statements_instances (OWNER_OBJECT_TYPE, OWNER_OBJECT_SCHEMA, OWNER_OBJECT_NAME)
    using hash;

create index STATEMENT_ID
    on performance_schema.prepared_statements_instances (STATEMENT_ID)
    using hash;

create index STATEMENT_NAME
    on performance_schema.prepared_statements_instances (STATEMENT_NAME)
    using hash;

