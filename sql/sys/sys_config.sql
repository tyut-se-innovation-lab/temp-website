create table sys.sys_config
(
    variable varchar(128)                        not null
        primary key,
    value    varchar(128)                        null,
    set_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    set_by   varchar(128)                        null
);

grant select on table sys.sys_config to 'mysql.sys'@localhost;

