create table performance_schema.replication_applier_configuration
(
    CHANNEL_NAME                                 char(64)                                 not null
        primary key using hash,
    DESIRED_DELAY                                int                                      not null,
    PRIVILEGE_CHECKS_USER                        text collate utf8mb3_bin                 null comment 'User name for the security context of the applier.',
    REQUIRE_ROW_FORMAT                           enum ('YES', 'NO')                       not null comment 'Indicates whether the channel shall only accept row based events.',
    REQUIRE_TABLE_PRIMARY_KEY_CHECK              enum ('STREAM', 'ON', 'OFF', 'GENERATE') not null comment 'Indicates what is the channel policy regarding tables without primary keys on create and alter table queries',
    ASSIGN_GTIDS_TO_ANONYMOUS_TRANSACTIONS_TYPE  enum ('OFF', 'LOCAL', 'UUID')            not null comment 'Indicates whether the channel will generate a new GTID for anonymous transactions. OFF means that anonymous transactions will remain anonymous. LOCAL means that anonymous transactions will be assigned a newly generated GTID based on server_uuid. UUID indicates that anonymous transactions will be assigned a newly generated GTID based on Assign_gtids_to_anonymous_transactions_value',
    ASSIGN_GTIDS_TO_ANONYMOUS_TRANSACTIONS_VALUE text collate utf8mb3_bin                 null comment 'Indicates the UUID used while generating GTIDs for anonymous transactions'
)
    engine = PERFORMANCE_SCHEMA;

