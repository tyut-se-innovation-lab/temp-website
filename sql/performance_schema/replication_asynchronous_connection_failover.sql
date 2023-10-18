create table performance_schema.replication_asynchronous_connection_failover
(
    CHANNEL_NAME      char(64) charset utf8mb3            not null comment 'The replication channel name that connects source and replica.',
    HOST              char(255) charset ascii             not null comment 'The source hostname that the replica will attempt to switch over the replication connection to in case of a failure.',
    PORT              int                                 not null comment 'The source port that the replica will attempt to switch over the replication connection to in case of a failure.',
    NETWORK_NAMESPACE char(64)                            null comment 'The source network namespace that the replica will attempt to switch over the replication connection to in case of a failure. If its value is empty, connections use the default (global) namespace.',
    WEIGHT            int unsigned                        not null comment 'The order in which the replica shall try to switch the connection over to when there are failures. Weight can be set to a number between 1 and 100, where 100 is the highest weight and 1 the lowest.',
    MANAGED_NAME      char(64) charset utf8mb3 default '' not null comment 'The name of the group which this server belongs to.'
)
    engine = PERFORMANCE_SCHEMA;

