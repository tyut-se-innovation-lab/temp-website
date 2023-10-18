create table performance_schema.file_instances
(
    FILE_NAME  varchar(512) not null
        primary key using hash,
    EVENT_NAME varchar(128) not null,
    OPEN_COUNT int unsigned not null
)
    engine = PERFORMANCE_SCHEMA;

create index EVENT_NAME
    on performance_schema.file_instances (EVENT_NAME)
    using hash;

