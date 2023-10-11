create table performance_schema.cond_instances
(
    NAME                  varchar(128)    not null,
    OBJECT_INSTANCE_BEGIN bigint unsigned not null
        primary key using hash
)
    engine = PERFORMANCE_SCHEMA;

create index NAME
    on performance_schema.cond_instances (NAME)
    using hash;

