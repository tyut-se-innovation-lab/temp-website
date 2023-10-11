create table performance_schema.setup_instruments
(
    NAME          varchar(128)                                                                                   not null
        primary key using hash,
    ENABLED       enum ('YES', 'NO')                                                                             not null,
    TIMED         enum ('YES', 'NO')                                                                             null,
    PROPERTIES    set ('singleton', 'progress', 'user', 'global_statistics', 'mutable', 'controlled_by_default') not null,
    FLAGS         set ('controlled')                                                                             null,
    VOLATILITY    int                                                                                            not null,
    DOCUMENTATION longtext                                                                                       null
)
    engine = PERFORMANCE_SCHEMA;

