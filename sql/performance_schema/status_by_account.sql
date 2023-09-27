create table performance_schema.status_by_account
(
    USER           char(32) collate utf8mb4_bin null,
    HOST           char(255) charset ascii      null,
    VARIABLE_NAME  varchar(64)                  not null,
    VARIABLE_VALUE varchar(1024)                null,
    constraint ACCOUNT
        unique (USER, HOST, VARIABLE_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

