create table performance_schema.rwlock_instances
(
    NAME                      varchar(128)    not null,
    OBJECT_INSTANCE_BEGIN     bigint unsigned not null
        primary key using hash,
    WRITE_LOCKED_BY_THREAD_ID bigint unsigned null,
    READ_LOCKED_BY_COUNT      int unsigned    not null
)
    engine = PERFORMANCE_SCHEMA;

create index NAME
    on performance_schema.rwlock_instances (NAME)
    using hash;

create index WRITE_LOCKED_BY_THREAD_ID
    on performance_schema.rwlock_instances (WRITE_LOCKED_BY_THREAD_ID)
    using hash;

