create table performance_schema.processlist
(
    ID               bigint unsigned               not null
        primary key using hash,
    USER             varchar(32)                   null,
    HOST             varchar(261) charset ascii    null,
    DB               varchar(64)                   null,
    COMMAND          varchar(16)                   null,
    TIME             bigint                        null,
    STATE            varchar(64)                   null,
    INFO             longtext                      null,
    EXECUTION_ENGINE enum ('PRIMARY', 'SECONDARY') null
)
    engine = PERFORMANCE_SCHEMA;

