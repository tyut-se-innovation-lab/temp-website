-- 投票系统按钮
-- 一级菜单
insert into sys_menu
values ('2000', '投票系统', '0', '4', 'vote', null, '', 0, 0, 'M', '0', '0', '', 'guide', 'admin', sysdate(), '', null,
        '投票系统目录');
-- 二级菜单
insert into sys_menu
values ('2001', '发起投票', '2000', '1', 'initiate', 'vote/initiate/index', '', 0, 0, 'C', '0', '0', 'vote:initiate',
        'user', 'admin', sysdate(), '', null, '发起投票表单');
insert into sys_menu
values ('2002', '参与投票', '2000', '2', 'join', 'vote/join/index', '', 0, 0, 'C', '0', '0', 'vote:join', 'peoples',
        'admin', sysdate(), '', null, '参与投票表单');
insert into sys_menu
values ('2003', '查看历史投票记录', '2000', '3', 'history', 'vote/history/index', '', 0, 0, 'C', '0', '0',
        'vote:history', 'tree-table', 'admin', sysdate(), '', null, '查看历史投票记录');
insert into sys_menu
values ('2004', '我的提议', '2000', '4', 'mine', 'vote/mine/index', '', 0, 0, 'C', '0', '0', 'vote:mine',
        'user', 'admin', sysdate(), '', null, '管理我的提议');

-- ----------------------------
-- 1、投票类型表
-- ----------------------------
drop table if exists selab_vote_type;
create table selab_vote_type
(
    vote_type_id   int         not null auto_increment comment '投票类型ID',
    vote_type_name varchar(50) not null comment '投票类型名称',
    remark         varchar(500) default '' comment '备注',
    primary key (vote_type_id)
) engine=innodb comment = '投票类型表';


-- ----------------------------
-- 2、投票详细信息存储表
-- ----------------------------
drop table if exists selab_vote;
create table selab_vote
(
    vote_id      int         not null auto_increment comment '投票类型ID',
    vote_type_id varchar(50) not null comment '投票类型名称',
    remark       varchar(500) default '' comment '备注',
    primary key (vote_type_id)
) engine=innodb comment = '投票信息表';


-- ----------------------------
-- 3、投票表
-- ----------------------------
drop table if exists selab_vote;
create table selab_vote
(
    vote_id      int         not null auto_increment comment '投票类型ID',
    vote_type_id varchar(50) not null comment '投票类型名称',
    create_by    varchar(64) default '' comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(64) default '' comment '更新者',
    update_time  datetime comment '更新时间',
    primary key (vote_type_id)
) engine=innodb comment = '投票信息表';