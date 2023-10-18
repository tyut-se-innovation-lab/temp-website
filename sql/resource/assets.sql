create table resource.assets
(
    total_assets      float        not null comment '总资产',
    disposable_assets float        not null comment '可支配资产',
    time              timestamp    not null comment '最后一次修改时间',
    id                int auto_increment
        primary key,
    description       varchar(255) null,
    percentage        float        not null
)
    collate = utf8mb3_croatian_ci;

