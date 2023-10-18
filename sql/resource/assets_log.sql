create table resource.assets_log
(
    assets_log_id     int auto_increment comment '日志id'
        primary key,
    user_name         varchar(255) not null comment '管理',
    description       varchar(255) not null comment '描述',
    change_assets     float        not null comment '花了多少',
    process_time      datetime     not null comment '处理时间',
    before_assets     float        not null comment '花费前可支配资产',
    afterwards_assets float        not null comment '花费后可支配资产'
)
    collate = utf8mb3_croatian_ci;

