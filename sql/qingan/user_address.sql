create table qingan.user_address
(
    id            bigint auto_increment comment '主键'
        primary key,
    name          varchar(32)  not null comment '用户姓名',
    username      varchar(32)  not null comment '用户名',
    phone         varchar(11)  not null comment '用户手机号',
    address       varchar(255) null comment '收货地址',
    consignee     varchar(32)  null comment '收货人',
    receive_phone varchar(11)  null comment '收货手机号',
    emark         varchar(100) null comment '备注',
    create_time   datetime     null comment '创建时间'
)
    comment '购物车' collate = utf8mb3_bin;

