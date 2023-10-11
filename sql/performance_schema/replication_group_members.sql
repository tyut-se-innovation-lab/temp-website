create table performance_schema.replication_group_members
(
    CHANNEL_NAME               char(64)                     not null,
    MEMBER_ID                  char(36) collate utf8mb4_bin not null,
    MEMBER_HOST                char(255) charset ascii      not null,
    MEMBER_PORT                int                          null,
    MEMBER_STATE               char(64) collate utf8mb4_bin not null,
    MEMBER_ROLE                char(64) collate utf8mb4_bin not null,
    MEMBER_VERSION             char(64) collate utf8mb4_bin not null,
    MEMBER_COMMUNICATION_STACK char(64) collate utf8mb4_bin not null
)
    engine = PERFORMANCE_SCHEMA;

