create table performance_schema.replication_asynchronous_connection_failover_managed
(
    CHANNEL_NAME  char(64) charset utf8mb3            not null comment 'The replication channel name that connects source and replica.',
    MANAGED_NAME  char(64) charset utf8mb3 default '' not null comment 'The name of the source which needs to be managed.',
    MANAGED_TYPE  char(64) charset utf8mb3 default '' not null comment 'Determines the managed type.',
    CONFIGURATION json                                null comment 'The data to help manage group. For Managed_type = GroupReplication, Configuration value should contain {"Primary_weight": 80, "Secondary_weight": 60}, so that it assigns weight=80 to PRIMARY of the group, and weight=60 for rest of the members in mysql.replication_asynchronous_connection_failover table.'
)
    engine = PERFORMANCE_SCHEMA;

