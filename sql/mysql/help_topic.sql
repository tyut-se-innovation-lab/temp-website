create table mysql.help_topic
(
    help_topic_id    int unsigned      not null
        primary key,
    name             char(64)          not null,
    help_category_id smallint unsigned not null,
    description      text              not null,
    example          text              not null,
    url              text              not null,
    constraint name
        unique (name)
)
    comment 'help topics' charset = utf8mb3;

