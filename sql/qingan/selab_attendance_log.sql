create table qingan.selab_attendance_log
(
    id             bigint auto_increment
        primary key,
    att_user       varchar(20) not null,
    att_start_time datetime    null,
    att_end_time   datetime    null,
    `interval`     varchar(11) null
);

