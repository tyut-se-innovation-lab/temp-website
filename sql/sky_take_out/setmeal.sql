create table sky_take_out.setmeal
(
    id          bigint auto_increment comment '主键'
        primary key,
    category_id bigint         not null comment '菜品分类id',
    name        varchar(32)    not null comment '套餐名称',
    price       decimal(10, 2) not null comment '套餐价格',
    status      int default 1  null comment '售卖状态 0:停售 1:起售',
    description varchar(255)   null comment '描述信息',
    image       varchar(255)   null comment '图片',
    create_time datetime       null comment '创建时间',
    update_time datetime       null comment '更新时间',
    create_user bigint         null comment '创建人',
    update_user bigint         null comment '修改人',
    constraint idx_setmeal_name
        unique (name)
)
    comment '套餐' collate = utf8mb3_bin;

