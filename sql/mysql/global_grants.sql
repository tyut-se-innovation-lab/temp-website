create table mysql.global_grants
(
    USER              char(32)                        default ''  not null,
    HOST              char(255) charset ascii         default ''  not null,
    PRIV              char(32) charset utf8mb3        default ''  not null,
    WITH_GRANT_OPTION enum ('N', 'Y') charset utf8mb3 default 'N' not null,
    primary key (USER, HOST, PRIV)
)
    comment 'Extended global grants' collate = utf8mb3_bin;

