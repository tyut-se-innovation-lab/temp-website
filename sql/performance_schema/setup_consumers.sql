create table performance_schema.setup_consumers
(
    NAME    varchar(64)        not null
        primary key using hash,
    ENABLED enum ('YES', 'NO') not null
)
    engine = PERFORMANCE_SCHEMA;

