create table performance_schema.tls_channel_status
(
    CHANNEL  varchar(128)  not null,
    PROPERTY varchar(128)  not null,
    VALUE    varchar(2048) not null
)
    engine = PERFORMANCE_SCHEMA;

