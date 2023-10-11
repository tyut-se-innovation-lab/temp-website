create table performance_schema.variables_by_thread
(
    THREAD_ID      bigint unsigned not null,
    VARIABLE_NAME  varchar(64)     not null,
    VARIABLE_VALUE varchar(1024)   null,
    primary key (THREAD_ID, VARIABLE_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

