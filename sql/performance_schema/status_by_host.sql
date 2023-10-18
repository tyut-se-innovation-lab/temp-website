create table performance_schema.status_by_host
(
    HOST           char(255) charset ascii null,
    VARIABLE_NAME  varchar(64)             not null,
    VARIABLE_VALUE varchar(1024)           null,
    constraint HOST
        unique (HOST, VARIABLE_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

