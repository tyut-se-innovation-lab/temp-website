create table desktop_platfrom.user_tasks_list
(
    task_id             bigint auto_increment comment '任务id'
        primary key,
    user_student_number bigint     not null comment '用户id',
    task_content        mediumtext not null comment '任务内容',
    task_start_time     datetime   not null comment '任务开始时间',
    task_end_time       datetime   not null comment '任务截止时间'
)
    charset = utf8mb3;

