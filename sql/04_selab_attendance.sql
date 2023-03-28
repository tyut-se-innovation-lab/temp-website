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
create table `selab_attendance_log` (
        `id` bigint (20) not null AUTO_INCREMENT,
        `att_user` varchar(20) not null ,
        `att_start_time` datetime ,
        `att_end_time` datetime ,
        `interval` varchar (11),
        primary key (id)
);
insert into `selab_attendance_log` (`id`, `att_user`, `att_start_time`, `att_end_time`, `interval`) values('7','2','2023-03-26 14:40:35','2023-03-26 16:40:37','2');
insert into `selab_attendance_log` (`id`, `att_user`, `att_start_time`, `att_end_time`, `interval`) values('6','2','2023-03-25 14:40:19','2023-03-25 16:40:22','2');
insert into `selab_attendance_log` (`id`, `att_user`, `att_start_time`, `att_end_time`, `interval`) values('5','2','2023-03-24 14:40:10','2023-03-24 17:40:13','3');
insert into `selab_attendance_log` (`id`, `att_user`, `att_start_time`, `att_end_time`, `interval`) values('4','1','2023-03-18 14:39:58','2023-03-18 15:40:01','2');
insert into `selab_attendance_log` (`id`, `att_user`, `att_start_time`, `att_end_time`, `interval`) values('3','1','2023-03-17 14:39:47','2023-03-17 15:39:50','1');
insert into `selab_attendance_log` (`id`, `att_user`, `att_start_time`, `att_end_time`, `interval`) values('2','1','2023-03-16 09:39:33','2023-03-16 12:39:39','2');

-- ----------------------------
-- 2. 签到信息统计表
-- ----------------------------
drop table if exists selab_attendance_log_statistics;
create table `selab_attendance_log_statistics` (
        `att_user` varchar(20) not null ,
        `interval` float (11)
);

