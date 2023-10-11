create table resource.report
(
    sequence_id        int          not null comment '上报id',
    report_name        varchar(255) not null comment '上报人',
    goods_id           int          not null comment '损坏物品id',
    goods_name         varchar(255) not null comment '物品名称',
    damage_description text         not null comment '损坏描述',
    reporting_time     datetime     not null comment '上报时间',
    process_time       datetime     not null comment '处理时间',
    goods_state        int          not null comment '未处理1    已处理已同意2   已处理但未同意3',
    dispose_name       varchar(255) not null comment '处理人',
    primary key (goods_id, sequence_id)
)
    collate = utf8mb3_croatian_ci;

