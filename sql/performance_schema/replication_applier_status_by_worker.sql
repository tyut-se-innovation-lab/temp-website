create table performance_schema.replication_applier_status_by_worker
(
    CHANNEL_NAME                                            char(64)           not null,
    WORKER_ID                                               bigint unsigned    not null,
    THREAD_ID                                               bigint unsigned    null,
    SERVICE_STATE                                           enum ('ON', 'OFF') not null,
    LAST_ERROR_NUMBER                                       int                not null,
    LAST_ERROR_MESSAGE                                      varchar(1024)      not null,
    LAST_ERROR_TIMESTAMP                                    timestamp(6)       not null,
    LAST_APPLIED_TRANSACTION                                char(57)           null,
    LAST_APPLIED_TRANSACTION_ORIGINAL_COMMIT_TIMESTAMP      timestamp(6)       not null,
    LAST_APPLIED_TRANSACTION_IMMEDIATE_COMMIT_TIMESTAMP     timestamp(6)       not null,
    LAST_APPLIED_TRANSACTION_START_APPLY_TIMESTAMP          timestamp(6)       not null,
    LAST_APPLIED_TRANSACTION_END_APPLY_TIMESTAMP            timestamp(6)       not null,
    APPLYING_TRANSACTION                                    char(57)           null,
    APPLYING_TRANSACTION_ORIGINAL_COMMIT_TIMESTAMP          timestamp(6)       not null,
    APPLYING_TRANSACTION_IMMEDIATE_COMMIT_TIMESTAMP         timestamp(6)       not null,
    APPLYING_TRANSACTION_START_APPLY_TIMESTAMP              timestamp(6)       not null,
    LAST_APPLIED_TRANSACTION_RETRIES_COUNT                  bigint unsigned    not null,
    LAST_APPLIED_TRANSACTION_LAST_TRANSIENT_ERROR_NUMBER    int                not null,
    LAST_APPLIED_TRANSACTION_LAST_TRANSIENT_ERROR_MESSAGE   varchar(1024)      null,
    LAST_APPLIED_TRANSACTION_LAST_TRANSIENT_ERROR_TIMESTAMP timestamp(6)       not null,
    APPLYING_TRANSACTION_RETRIES_COUNT                      bigint unsigned    not null,
    APPLYING_TRANSACTION_LAST_TRANSIENT_ERROR_NUMBER        int                not null,
    APPLYING_TRANSACTION_LAST_TRANSIENT_ERROR_MESSAGE       varchar(1024)      null,
    APPLYING_TRANSACTION_LAST_TRANSIENT_ERROR_TIMESTAMP     timestamp(6)       not null,
    primary key (CHANNEL_NAME, WORKER_ID) using hash
)
    engine = PERFORMANCE_SCHEMA;

create index THREAD_ID
    on performance_schema.replication_applier_status_by_worker (THREAD_ID)
    using hash;

