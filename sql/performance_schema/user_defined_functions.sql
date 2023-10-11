create table performance_schema.user_defined_functions
(
    UDF_NAME        varchar(64)   not null
        primary key using hash,
    UDF_RETURN_TYPE varchar(20)   not null,
    UDF_TYPE        varchar(20)   not null,
    UDF_LIBRARY     varchar(1024) null,
    UDF_USAGE_COUNT bigint        null
)
    engine = PERFORMANCE_SCHEMA;

