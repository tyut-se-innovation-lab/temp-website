create table mysql.replication_group_configuration_version
(
    name    char(255) charset ascii not null comment 'The configuration name.'
        primary key,
    version bigint unsigned         not null comment 'The version of the configuration name.'
)
    comment 'The group configuration version.';

