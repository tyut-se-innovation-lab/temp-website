create table libary.user
(
    user_id        int auto_increment comment '用户id',
    account_number varchar(30) not null comment '账号',
    password       varchar(32) not null comment '密码',
    name           varchar(20) not null comment '姓名',
    student_number bigint      not null comment '学号',
    gender         int         not null comment '性别',
    phone          varchar(20) not null comment '电话号码',
    post           varchar(30) not null comment '邮箱',
    register_time  timestamp   not null comment '注册时间',
    login_status   int         not null comment '登录状态',
    role_id        int         not null comment '角色id',
    primary key (user_id, student_number)
)
    charset = utf8mb3;

