create table mysql.default_roles
(
    HOST              char(255) charset ascii default ''  not null,
    USER              char(32)                default ''  not null,
    DEFAULT_ROLE_HOST char(255) charset ascii default '%' not null,
    DEFAULT_ROLE_USER char(32)                default ''  not null,
    primary key (HOST, USER, DEFAULT_ROLE_HOST, DEFAULT_ROLE_USER)
)
    comment 'Default roles' collate = utf8mb3_bin;

