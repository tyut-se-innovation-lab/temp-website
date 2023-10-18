create table libary.user_bug_message
(
    bug_id              bigint auto_increment comment '报错id'
        primary key,
    bug_titile          mediumtext not null comment '报错主题',
    bug_solve           longtext   not null comment '报错解决',
    release_time        datetime   not null comment '发布时间',
    user_student_number bigint     not null comment '用户id'
);

