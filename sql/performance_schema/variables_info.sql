create table performance_schema.variables_info
(
    VARIABLE_NAME   varchar(64)                                                                                                                            not null,
    VARIABLE_SOURCE enum ('COMPILED', 'GLOBAL', 'SERVER', 'EXPLICIT', 'EXTRA', 'USER', 'LOGIN', 'COMMAND_LINE', 'PERSISTED', 'DYNAMIC') default 'COMPILED' null,
    VARIABLE_PATH   varchar(1024)                                                                                                                          null,
    MIN_VALUE       varchar(64)                                                                                                                            null,
    MAX_VALUE       varchar(64)                                                                                                                            null,
    SET_TIME        timestamp(6)                                                                                                                           null,
    SET_USER        char(32) collate utf8mb4_bin                                                                                                           null,
    SET_HOST        char(255) charset ascii                                                                                                                null
)
    engine = PERFORMANCE_SCHEMA;

