create table performance_schema.threads
(
    THREAD_ID             bigint unsigned               not null
        primary key using hash,
    NAME                  varchar(128)                  not null,
    TYPE                  varchar(10)                   not null,
    PROCESSLIST_ID        bigint unsigned               null,
    PROCESSLIST_USER      varchar(32)                   null,
    PROCESSLIST_HOST      varchar(255) charset ascii    null,
    PROCESSLIST_DB        varchar(64)                   null,
    PROCESSLIST_COMMAND   varchar(16)                   null,
    PROCESSLIST_TIME      bigint                        null,
    PROCESSLIST_STATE     varchar(64)                   null,
    PROCESSLIST_INFO      longtext                      null,
    PARENT_THREAD_ID      bigint unsigned               null,
    ROLE                  varchar(64)                   null,
    INSTRUMENTED          enum ('YES', 'NO')            not null,
    HISTORY               enum ('YES', 'NO')            not null,
    CONNECTION_TYPE       varchar(16)                   null,
    THREAD_OS_ID          bigint unsigned               null,
    RESOURCE_GROUP        varchar(64)                   null,
    EXECUTION_ENGINE      enum ('PRIMARY', 'SECONDARY') null,
    CONTROLLED_MEMORY     bigint unsigned               not null,
    MAX_CONTROLLED_MEMORY bigint unsigned               not null,
    TOTAL_MEMORY          bigint unsigned               not null,
    MAX_TOTAL_MEMORY      bigint unsigned               not null
)
    engine = PERFORMANCE_SCHEMA;

create index NAME
    on performance_schema.threads (NAME)
    using hash;

create index PROCESSLIST_ACCOUNT
    on performance_schema.threads (PROCESSLIST_USER, PROCESSLIST_HOST)
    using hash;

create index PROCESSLIST_HOST
    on performance_schema.threads (PROCESSLIST_HOST)
    using hash;

create index PROCESSLIST_ID
    on performance_schema.threads (PROCESSLIST_ID)
    using hash;

create index RESOURCE_GROUP
    on performance_schema.threads (RESOURCE_GROUP)
    using hash;

create index THREAD_OS_ID
    on performance_schema.threads (THREAD_OS_ID)
    using hash;

