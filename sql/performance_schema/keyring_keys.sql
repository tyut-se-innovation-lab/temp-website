create table performance_schema.keyring_keys
(
    KEY_ID         varchar(255) not null,
    KEY_OWNER      varchar(255) null,
    BACKEND_KEY_ID varchar(255) null
)
    engine = PERFORMANCE_SCHEMA
    collate = utf8mb4_bin;

