create table mysql.servers
(
    Server_name char(64)                default '' not null
        primary key,
    Host        char(255) charset ascii default '' not null,
    Db          char(64)                default '' not null,
    Username    char(64)                default '' not null,
    Password    char(64)                default '' not null,
    Port        int                     default 0  not null,
    Socket      char(64)                default '' not null,
    Wrapper     char(64)                default '' not null,
    Owner       char(64)                default '' not null
)
    comment 'MySQL Foreign Servers table' charset = utf8mb3;

