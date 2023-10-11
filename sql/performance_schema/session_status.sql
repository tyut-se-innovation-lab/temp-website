create table performance_schema.session_status
(
    VARIABLE_NAME  varchar(64)   not null
        primary key using hash,
    VARIABLE_VALUE varchar(1024) null
)
    engine = PERFORMANCE_SCHEMA;

