-- 投票系统按钮
-- 一级菜单
insert into sys_menu
values ('2000', '投票系统', '0', '4', 'vote', null, '', 1, 0, 'M', '0', '0', '', 'checkbox', 'admin', sysdate(), '',
        null, '投票系统目录');
-- 二级菜单
insert into sys_menu
values ('2001', '发起投票', '2000', '1', 'initiate', 'vote/initiate/index', '', 1, 1, 'C', '0', '0', 'vote:initiate',
        'edit', 'admin', sysdate(), '', null, '发起投票表单');
insert into sys_menu
values ('2002', '参与投票', '2000', '2', 'join', 'vote/join/index', '', 1, 0, 'C', '0', '0', 'vote:join',
        'exit-fullscreen',
        'admin', sysdate(), '', null, '参与投票表单');
insert into sys_menu
values ('2003', '查看历史投票记录', '2000', '3', 'history', 'vote/history/index', '', 1, 1, 'C', '0', '0',
        'vote:history', 'documentation', 'admin', sysdate(), '', null, '查看历史投票记录');
insert into sys_menu
values ('2004', '我的提议', '2000', '4', 'mine', 'vote/mine/index', '', 1, 1, 'C', '0', '0', 'vote:mine',
        'search', 'admin', sysdate(), '', null, '管理我的提议');
insert into sys_menu
values ('2011', '设置投票策略', '2000', '5', 'strategy', 'vote/management/index', '', 1, 1, 'C', '0', '0', 'vote:management',
        'search', 'admin', sysdate(), '', null, '设置投票策略');

-- ----------------------------
-- 1、投票选项存储表
-- ----------------------------
drop table if exists selab_vote_option;
create table selab_vote_option
(
    id          bigint      not null auto_increment comment '投票选项ID', -- 唯一id --
    parent_id   bigint      not null comment '父选项',  -- -1 --
    vote_id     bigint      not null comment '所隶属于的投票id',
    option_type char(1)     not null comment '投票类型',
    content     varchar(30) not null comment '内容',
    primary key (id)
) engine=innodb comment = '投票详细信息表';


-- ----------------------------
-- 2、投票表
-- ----------------------------
drop table if exists selab_vote_info;
create table selab_vote_info
(
    id           bigint       not null auto_increment comment '数据唯一标识',
    user_id      varchar(100) not null comment '发起投票的用户ID（加密后）',
    title        tinytext     not null comment '投票标题',
    content      text         not null comment '投票简介',
    status       int          not null comment '状态',
    deadline     datetime     not null comment '截止时间',
    create_time  datetime     not null comment '创建时间',
    weight       int          not null comment '权重表',
    primary key (id)
) engine=innodb comment = '投票信息表';

-- ----------------------------
-- 3、投票结果表
-- ----------------------------
drop table if exists selab_vote_result;
create table selab_vote_result
(
    id             bigint       not null auto_increment comment '数据唯一标识',
    vote_option_id bigint       not null comment '投票选项ID',
    user_id        varchar(100) not null comment '参与投票的用户ID（加密后）',
    content        text         not null comment '投票内容',  -- 如果是文本框，就存文本框写的，不是的话就是本选项的内容 --
    isEnable       bool         default TRUE comment '是否有效',
    create_time    datetime comment '投票时间',
    primary key (id)
) engine=innodb comment = '投票结果表';

-- ----------------------------
-- 4、投票权重表
-- ----------------------------
drop table if exists selab_vote_weight;
create table selab_vote_weight
(
    id              bigint          not null    auto_increment comment '数据唯一标识',
    voteManager     int             default 1   comment '投票管理员',
    DEVManager      int             default 1   comment '开发组',
    CSManager       int             default 1   comment '网安组',
    DEVLeader       int             default 1   comment '开发组管理员',
    CSLeader        int             default 1   comment '开发组管理员',
    DEVMember       int             default 1   comment '开发组管理员',
    CSMember        int             default 1   comment '开发组管理员',
    primary key(id)
)