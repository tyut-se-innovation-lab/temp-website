create table performance_schema.setup_threads
(
    NAME          varchar(128)              not null
        primary key using hash,
    ENABLED       enum ('YES', 'NO')        not null,
    HISTORY       enum ('YES', 'NO')        not null,
    PROPERTIES    set ('singleton', 'user') not null,
    VOLATILITY    int                       not null,
    DOCUMENTATION longtext                  null
)
    engine = PERFORMANCE_SCHEMA;

