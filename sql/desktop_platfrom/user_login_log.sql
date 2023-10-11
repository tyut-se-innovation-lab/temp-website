create table desktop_platfrom.user_login_log
(
    log_id              bigint auto_increment comment '登录日志id'
        primary key,
    user_student_number bigint       not null comment '用户id',
    login_time          datetime     not null comment '登录时间',
    login_ip            varchar(128) not null comment '登录ip'
);

