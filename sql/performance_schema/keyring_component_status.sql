create table performance_schema.keyring_component_status
(
    STATUS_KEY   varchar(256)  not null,
    STATUS_VALUE varchar(1024) not null
)
    engine = PERFORMANCE_SCHEMA;

