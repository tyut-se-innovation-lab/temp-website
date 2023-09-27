create table mysql.replication_asynchronous_connection_failover_managed
(
    Channel_name  char(64)            not null comment 'The replication channel name that connects source and replica.',
    Managed_name  char(64) default '' not null comment 'The name of the source which needs to be managed.',
    Managed_type  char(64) default '' not null comment 'Determines the managed type.',
    Configuration json                null comment 'The data to help manage group. For Managed_type = GroupReplication, Configuration value should contain {"Primary_weight": 80, "Secondary_weight": 60}, so that it assigns weight=80 to PRIMARY of the group, and weight=60 for rest of the members in mysql.replication_asynchronous_connection_failover table.',
    primary key (Channel_name, Managed_name)
)
    comment 'The managed source configuration details' charset = utf8mb3;

