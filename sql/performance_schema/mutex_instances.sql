create table performance_schema.mutex_instances
(
    NAME                  varchar(128)    not null,
    OBJECT_INSTANCE_BEGIN bigint unsigned not null
        primary key using hash,
    LOCKED_BY_THREAD_ID   bigint unsigned null
)
    engine = PERFORMANCE_SCHEMA;

create index LOCKED_BY_THREAD_ID
    on performance_schema.mutex_instances (LOCKED_BY_THREAD_ID)
    using hash;

create index NAME
    on performance_schema.mutex_instances (NAME)
    using hash;

