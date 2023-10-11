create table performance_schema.replication_group_member_stats
(
    CHANNEL_NAME                               char(64)                     not null,
    VIEW_ID                                    char(60) collate utf8mb4_bin not null,
    MEMBER_ID                                  char(36) collate utf8mb4_bin not null,
    COUNT_TRANSACTIONS_IN_QUEUE                bigint unsigned              not null,
    COUNT_TRANSACTIONS_CHECKED                 bigint unsigned              not null,
    COUNT_CONFLICTS_DETECTED                   bigint unsigned              not null,
    COUNT_TRANSACTIONS_ROWS_VALIDATING         bigint unsigned              not null,
    TRANSACTIONS_COMMITTED_ALL_MEMBERS         longtext                     not null,
    LAST_CONFLICT_FREE_TRANSACTION             text                         not null,
    COUNT_TRANSACTIONS_REMOTE_IN_APPLIER_QUEUE bigint unsigned              not null,
    COUNT_TRANSACTIONS_REMOTE_APPLIED          bigint unsigned              not null,
    COUNT_TRANSACTIONS_LOCAL_PROPOSED          bigint unsigned              not null,
    COUNT_TRANSACTIONS_LOCAL_ROLLBACK          bigint unsigned              not null
)
    engine = PERFORMANCE_SCHEMA;

