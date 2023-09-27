create table performance_schema.data_lock_waits
(
    ENGINE                           varchar(32)     not null,
    REQUESTING_ENGINE_LOCK_ID        varchar(128)    not null,
    REQUESTING_ENGINE_TRANSACTION_ID bigint unsigned null,
    REQUESTING_THREAD_ID             bigint unsigned null,
    REQUESTING_EVENT_ID              bigint unsigned null,
    REQUESTING_OBJECT_INSTANCE_BEGIN bigint unsigned not null,
    BLOCKING_ENGINE_LOCK_ID          varchar(128)    not null,
    BLOCKING_ENGINE_TRANSACTION_ID   bigint unsigned null,
    BLOCKING_THREAD_ID               bigint unsigned null,
    BLOCKING_EVENT_ID                bigint unsigned null,
    BLOCKING_OBJECT_INSTANCE_BEGIN   bigint unsigned not null
)
    engine = PERFORMANCE_SCHEMA;

create index BLOCKING_ENGINE_LOCK_ID
    on performance_schema.data_lock_waits (BLOCKING_ENGINE_LOCK_ID, ENGINE)
    using hash;

create index BLOCKING_ENGINE_TRANSACTION_ID
    on performance_schema.data_lock_waits (BLOCKING_ENGINE_TRANSACTION_ID, ENGINE)
    using hash;

create index BLOCKING_THREAD_ID
    on performance_schema.data_lock_waits (BLOCKING_THREAD_ID, BLOCKING_EVENT_ID)
    using hash;

create index REQUESTING_ENGINE_LOCK_ID
    on performance_schema.data_lock_waits (REQUESTING_ENGINE_LOCK_ID, ENGINE)
    using hash;

create index REQUESTING_ENGINE_TRANSACTION_ID
    on performance_schema.data_lock_waits (REQUESTING_ENGINE_TRANSACTION_ID, ENGINE)
    using hash;

create index REQUESTING_THREAD_ID
    on performance_schema.data_lock_waits (REQUESTING_THREAD_ID, REQUESTING_EVENT_ID)
    using hash;

