create table resource.goods
(
    goods_id    int          not null comment '物品id
'
        primary key,
    goods_name  varchar(255) null comment '物品名称',
    goods_state varchar(255) not null comment '物品状态（正常1 损坏2 处理中3）',
    goods_Image varchar(255) null comment '物品图片'
)
    collate = utf8mb3_croatian_ci;

