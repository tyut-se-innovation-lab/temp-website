-- 空课表系统按钮
-- 一级菜单
insert into sys_menu
values ('2005', '空课表系统', '0', '5', 'schedule', null, '', 1, 0, 'M', '0', '0', '', 'build', 'admin', sysdate(),
        '',
        null, '空课表目录');
-- 二级菜单
insert into sys_menu
values ('2006', '我的课表', '2005', '1', 'mine', null, '', 1, 0, 'M', '0', '0', '', 'build', 'admin', sysdate(), '',
        null, '我的课表目录');
insert into sys_menu
-- 功能菜单
values ('2007', '上传课表', '2006', '1', 'upload', 'schedule/mine/upload/index', '', 1, 1, 'C', '0', '0',
        'schedule:mine:upload',
        'edit', 'admin', sysdate(), '', null, '发起投票表单');
insert into sys_menu
values ('2008', '展示我的课表', '2006', '2', 'display', 'schedule/mine/display/index', '', 1, 1, 'C', '0', '0',
        'schedule:mine:display',
        'eye-open', 'admin', sysdate(), '', null, '发起投票表单');
insert into sys_menu
values ('2009', '查看空课人员', '2005', '3', 'leisure', 'schedule/leisure/index', '', 1, 0, 'C', '0', '0',
        'schedule:leisure',
        'peoples',
        'admin', sysdate(), '', null, '查看空课人员');
insert into sys_menu
values ('2010', '随机排课', '2005', '4', 'arrangement', 'schedule/arrangement/index', '', 1, 1, 'C', '0', '0',
        'schedule:arrangement', 'documentation', 'admin', sysdate(), '', null, '随机排课');

-- ----------------------------
-- 1、课表存储
-- ----------------------------
drop table if exists selab_schedule_info;
create table selab_schedule_info
(
    id           bigint   not null auto_increment comment '数据唯一标识',
    user         bigint   not null comment '用户id',
    `period`     int      not null comment '第几节课',
    week         int      not null comment '星期几',
    week_no      int      not null comment '第几周',
    status       int      not null comment '状态',
    course_title varchar(30) default '' comment '课程名称',
    create_time  datetime not null comment '创建时间',
    update_time  datetime not null comment '修改时间',
    primary key (id)
) engine=innodb comment = '课表信息';