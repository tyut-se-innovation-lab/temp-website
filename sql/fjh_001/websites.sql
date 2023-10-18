create table fjh_001.websites
(
    id      int auto_increment
        primary key,
    name    char(20)     default '' not null comment '站点名称',
    url     varchar(255) default '' not null,
    alexa   int          default 0  not null comment 'Alexa 排名',
    country char(10)     default '' not null comment '国家'
)
    charset = utf8mb3;

