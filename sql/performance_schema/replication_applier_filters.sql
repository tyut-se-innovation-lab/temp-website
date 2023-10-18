create table performance_schema.replication_applier_filters
(
    CHANNEL_NAME  char(64)                                                                                                                      not null,
    FILTER_NAME   char(64)                                                                                                                      not null,
    FILTER_RULE   longtext                                                                                                                      not null,
    CONFIGURED_BY enum ('STARTUP_OPTIONS', 'CHANGE_REPLICATION_FILTER', 'STARTUP_OPTIONS_FOR_CHANNEL', 'CHANGE_REPLICATION_FILTER_FOR_CHANNEL') not null,
    ACTIVE_SINCE  timestamp(6)                                                                                                                  not null,
    COUNTER       bigint unsigned default '0'                                                                                                   not null
)
    engine = PERFORMANCE_SCHEMA;

