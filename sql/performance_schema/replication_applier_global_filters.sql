create table performance_schema.replication_applier_global_filters
(
    FILTER_NAME   char(64)                                              not null,
    FILTER_RULE   longtext                                              not null,
    CONFIGURED_BY enum ('STARTUP_OPTIONS', 'CHANGE_REPLICATION_FILTER') not null,
    ACTIVE_SINCE  timestamp(6)                                          not null
)
    engine = PERFORMANCE_SCHEMA;

