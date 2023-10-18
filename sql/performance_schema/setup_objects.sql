create table performance_schema.setup_objects
(
    OBJECT_TYPE   enum ('EVENT', 'FUNCTION', 'PROCEDURE', 'TABLE', 'TRIGGER') default 'TABLE' not null,
    OBJECT_SCHEMA varchar(64)                                                 default '%'     null,
    OBJECT_NAME   varchar(64)                                                 default '%'     not null,
    ENABLED       enum ('YES', 'NO')                                          default 'YES'   not null,
    TIMED         enum ('YES', 'NO')                                          default 'YES'   not null,
    constraint OBJECT
        unique (OBJECT_TYPE, OBJECT_SCHEMA, OBJECT_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

