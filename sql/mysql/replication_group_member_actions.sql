create table mysql.replication_group_member_actions
(
    name           char(255) charset ascii not null comment 'The action name.',
    event          char(64) charset ascii  not null comment 'The event that will trigger the action.',
    enabled        tinyint(1)              not null comment 'Whether the action is enabled.',
    type           char(64) charset ascii  not null comment 'The action type.',
    priority       tinyint unsigned        not null comment 'The order on which the action will be run, value between 1 and 100, lower values first.',
    error_handling char(64) charset ascii  not null comment 'On errors during the action will be handled: IGNORE, CRITICAL.',
    primary key (name, event)
)
    comment 'The member actions configuration.';

create index event
    on mysql.replication_group_member_actions (event);

