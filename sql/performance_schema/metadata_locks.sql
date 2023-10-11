create table performance_schema.metadata_locks
(
    OBJECT_TYPE           varchar(64)     not null,
    OBJECT_SCHEMA         varchar(64)     null,
    OBJECT_NAME           varchar(64)     null,
    COLUMN_NAME           varchar(64)     null,
    OBJECT_INSTANCE_BEGIN bigint unsigned not null
        primary key using hash,
    LOCK_TYPE             varchar(32)     not null,
    LOCK_DURATION         varchar(32)     not null,
    LOCK_STATUS           varchar(32)     not null,
    SOURCE                varchar(64)     null,
    OWNER_THREAD_ID       bigint unsigned null,
    OWNER_EVENT_ID        bigint unsigned null
)
    engine = PERFORMANCE_SCHEMA;

create index OBJECT_TYPE
    on performance_schema.metadata_locks (OBJECT_TYPE, OBJECT_SCHEMA, OBJECT_NAME, COLUMN_NAME)
    using hash;

create index OWNER_THREAD_ID
    on performance_schema.metadata_locks (OWNER_THREAD_ID, OWNER_EVENT_ID)
    using hash;

