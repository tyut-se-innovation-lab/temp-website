create table performance_schema.data_locks
(
    ENGINE                varchar(32)     not null,
    ENGINE_LOCK_ID        varchar(128)    not null,
    ENGINE_TRANSACTION_ID bigint unsigned null,
    THREAD_ID             bigint unsigned null,
    EVENT_ID              bigint unsigned null,
    OBJECT_SCHEMA         varchar(64)     null,
    OBJECT_NAME           varchar(64)     null,
    PARTITION_NAME        varchar(64)     null,
    SUBPARTITION_NAME     varchar(64)     null,
    INDEX_NAME            varchar(64)     null,
    OBJECT_INSTANCE_BEGIN bigint unsigned not null,
    LOCK_TYPE             varchar(32)     not null,
    LOCK_MODE             varchar(32)     not null,
    LOCK_STATUS           varchar(32)     not null,
    LOCK_DATA             varchar(8192)   null,
    primary key (ENGINE_LOCK_ID, ENGINE) using hash
)
    engine = PERFORMANCE_SCHEMA;

create index ENGINE_TRANSACTION_ID
    on performance_schema.data_locks (ENGINE_TRANSACTION_ID, ENGINE)
    using hash;

create index OBJECT_SCHEMA
    on performance_schema.data_locks (OBJECT_SCHEMA, OBJECT_NAME, PARTITION_NAME, SUBPARTITION_NAME)
    using hash;

create index THREAD_ID
    on performance_schema.data_locks (THREAD_ID, EVENT_ID)
    using hash;

