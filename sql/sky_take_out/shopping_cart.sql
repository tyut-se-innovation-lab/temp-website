create table sky_take_out.shopping_cart
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(32)    null comment '商品名称',
    image       varchar(255)   null comment '图片',
    user_id     bigint         not null comment '主键',
    dish_id     bigint         null comment '菜品id',
    setmeal_id  bigint         null comment '套餐id',
    dish_flavor varchar(50)    null comment '口味',
    number      int default 1  not null comment '数量',
    amount      decimal(10, 2) not null comment '金额',
    create_time datetime       null comment '创建时间'
)
    comment '购物车' collate = utf8mb3_bin;

