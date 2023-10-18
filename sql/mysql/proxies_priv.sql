create table mysql.proxies_priv
(
    Host         char(255) charset ascii default ''                not null,
    User         char(32)                default ''                not null,
    Proxied_host char(255) charset ascii default ''                not null,
    Proxied_user char(32)                default ''                not null,
    With_grant   tinyint(1)              default 0                 not null,
    Grantor      varchar(288)            default ''                not null,
    Timestamp    timestamp               default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    primary key (Host, User, Proxied_host, Proxied_user)
)
    comment 'User proxy privileges' collate = utf8mb3_bin;

create index Grantor
    on mysql.proxies_priv (Grantor);

