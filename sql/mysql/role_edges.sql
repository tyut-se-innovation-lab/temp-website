create table mysql.role_edges
(
    FROM_HOST         char(255) charset ascii         default ''  not null,
    FROM_USER         char(32)                        default ''  not null,
    TO_HOST           char(255) charset ascii         default ''  not null,
    TO_USER           char(32)                        default ''  not null,
    WITH_ADMIN_OPTION enum ('N', 'Y') charset utf8mb3 default 'N' not null,
    primary key (FROM_HOST, FROM_USER, TO_HOST, TO_USER)
)
    comment 'Role hierarchy and role grants' collate = utf8mb3_bin;

