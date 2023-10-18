create table performance_schema.replication_applier_status
(
    CHANNEL_NAME               char(64)           not null
        primary key using hash,
    SERVICE_STATE              enum ('ON', 'OFF') not null,
    REMAINING_DELAY            int unsigned       null,
    COUNT_TRANSACTIONS_RETRIES bigint unsigned    not null
)
    engine = PERFORMANCE_SCHEMA;

