create table desktop_platfrom.user_file_up
(
    up_id               bigint auto_increment comment '上传id'
        primary key,
    user_student_number bigint       not null comment '用户id',
    up_file_path        varchar(255) not null comment '上传文件路径',
    up_time             datetime     not null comment '上传时间',
    up_ip               varchar(128) not null comment '上传者ip'
);

