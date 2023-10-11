create table qingan.goods
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(32)    not null comment '商品名称',
    goods_id    bigint         not null comment '商品分类id',
    price       decimal(10, 2) null comment '商品价格',
    image       varchar(255)   null comment '图片',
    description varchar(255)   null comment '描述信息',
    status      int default 1  null comment '0 停售 1 起售',
    create_time datetime       null comment '创建时间',
    update_time datetime       null comment '更新时间',
    create_user bigint         null comment '创建人',
    update_user bigint         null comment '修改人',
    constraint idx_dish_name
        unique (name)
)
    comment '商品' collate = utf8mb3_bin;

