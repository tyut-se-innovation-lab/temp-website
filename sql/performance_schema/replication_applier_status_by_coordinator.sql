create table performance_schema.replication_applier_status_by_coordinator
(
    CHANNEL_NAME                                          char(64)           not null
        primary key using hash,
    THREAD_ID                                             bigint unsigned    null,
    SERVICE_STATE                                         enum ('ON', 'OFF') not null,
    LAST_ERROR_NUMBER                                     int                not null,
    LAST_ERROR_MESSAGE                                    varchar(1024)      not null,
    LAST_ERROR_TIMESTAMP                                  timestamp(6)       not null,
    LAST_PROCESSED_TRANSACTION                            char(57)           null,
    LAST_PROCESSED_TRANSACTION_ORIGINAL_COMMIT_TIMESTAMP  timestamp(6)       not null,
    LAST_PROCESSED_TRANSACTION_IMMEDIATE_COMMIT_TIMESTAMP timestamp(6)       not null,
    LAST_PROCESSED_TRANSACTION_START_BUFFER_TIMESTAMP     timestamp(6)       not null,
    LAST_PROCESSED_TRANSACTION_END_BUFFER_TIMESTAMP       timestamp(6)       not null,
    PROCESSING_TRANSACTION                                char(57)           null,
    PROCESSING_TRANSACTION_ORIGINAL_COMMIT_TIMESTAMP      timestamp(6)       not null,
    PROCESSING_TRANSACTION_IMMEDIATE_COMMIT_TIMESTAMP     timestamp(6)       not null,
    PROCESSING_TRANSACTION_START_BUFFER_TIMESTAMP         timestamp(6)       not null
)
    engine = PERFORMANCE_SCHEMA;

create index THREAD_ID
    on performance_schema.replication_applier_status_by_coordinator (THREAD_ID)
    using hash;

