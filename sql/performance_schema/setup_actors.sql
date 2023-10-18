create table performance_schema.setup_actors
(
    HOST    char(255) charset ascii      default '%'   not null,
    USER    char(32) collate utf8mb4_bin default '%'   not null,
    ROLE    char(32) collate utf8mb4_bin default '%'   not null,
    ENABLED enum ('YES', 'NO')           default 'YES' not null,
    HISTORY enum ('YES', 'NO')           default 'YES' not null,
    primary key (HOST, USER, ROLE) using hash
)
    engine = PERFORMANCE_SCHEMA;

