create table performance_schema.replication_connection_status
(
    CHANNEL_NAME                                       char(64)                         not null
        primary key using hash,
    GROUP_NAME                                         char(36) collate utf8mb4_bin     not null,
    SOURCE_UUID                                        char(36) collate utf8mb4_bin     not null,
    THREAD_ID                                          bigint unsigned                  null,
    SERVICE_STATE                                      enum ('ON', 'OFF', 'CONNECTING') not null,
    COUNT_RECEIVED_HEARTBEATS                          bigint unsigned default '0'      not null,
    LAST_HEARTBEAT_TIMESTAMP                           timestamp(6)                     not null comment 'Shows when the most recent heartbeat signal was received.',
    RECEIVED_TRANSACTION_SET                           longtext                         not null,
    LAST_ERROR_NUMBER                                  int                              not null,
    LAST_ERROR_MESSAGE                                 varchar(1024)                    not null,
    LAST_ERROR_TIMESTAMP                               timestamp(6)                     not null,
    LAST_QUEUED_TRANSACTION                            char(57)                         null,
    LAST_QUEUED_TRANSACTION_ORIGINAL_COMMIT_TIMESTAMP  timestamp(6)                     not null,
    LAST_QUEUED_TRANSACTION_IMMEDIATE_COMMIT_TIMESTAMP timestamp(6)                     not null,
    LAST_QUEUED_TRANSACTION_START_QUEUE_TIMESTAMP      timestamp(6)                     not null,
    LAST_QUEUED_TRANSACTION_END_QUEUE_TIMESTAMP        timestamp(6)                     not null,
    QUEUEING_TRANSACTION                               char(57)                         null,
    QUEUEING_TRANSACTION_ORIGINAL_COMMIT_TIMESTAMP     timestamp(6)                     not null,
    QUEUEING_TRANSACTION_IMMEDIATE_COMMIT_TIMESTAMP    timestamp(6)                     not null,
    QUEUEING_TRANSACTION_START_QUEUE_TIMESTAMP         timestamp(6)                     not null
)
    engine = PERFORMANCE_SCHEMA;

create index THREAD_ID
    on performance_schema.replication_connection_status (THREAD_ID)
    using hash;

