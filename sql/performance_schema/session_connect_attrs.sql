create table performance_schema.session_connect_attrs
(
    PROCESSLIST_ID   bigint unsigned not null,
    ATTR_NAME        varchar(32)     not null,
    ATTR_VALUE       varchar(1024)   null,
    ORDINAL_POSITION int             null,
    primary key (PROCESSLIST_ID, ATTR_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA
    collate = utf8mb4_bin;

