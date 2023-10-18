create table libary.user_role
(
    role_id int auto_increment comment '角色id'
        primary key,
    duty    varchar(5) not null comment '角色说明'
)
    charset = utf8mb3;

