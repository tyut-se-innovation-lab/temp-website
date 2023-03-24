-- 签到系统按钮
-- 一级菜单
insert into sys_menu
values ('2012', '签到', '0', '6', 'attendance', null, '', 1, 0, 'M', '0', '0', '', 'build', 'admin', sysdate(), '',
        null, '投票系统目录');
-- 二级菜单
insert into sys_menu
values ('2013', '今日签到', '2012', '1', 'att', 'attendance/sign', '', 1, 1, 'C', '0', '0', 'attendance:sign',
        'edit', 'admin', sysdate(), '', null, '签到表单');
insert into sys_menu
values ('2014', '查看记录', '2012', '2', 'join', 'attendance/log', '', 1, 0, 'C', '0', '0', 'attendance:log',
        'exit-fullscreen',
        'admin', sysdate(), '', null, '查看记录表单');


-- ----------------------------
-- 1、签到信息存储表
-- ----------------------------
drop table if exists selab_attendance_log;
create table selab_attendance_log(
    att_user bigint not null comment '签到发起人',
    att_start_time date comment '签到时间',
    att_end_time date comment '签退时间',
    `interval` float comment '本次打卡时间(单位：h)'
);
insert into selab_attendance_log values
(1,'2023-03-23 20:47:19','2023-03-23 22:47:19',2),
(1,'2023-03-24 20:47:19','2023-03-24 22:47:19',2),
(1,'2023-03-25 20:47:19','2023-03-25 22:47:19',2);
