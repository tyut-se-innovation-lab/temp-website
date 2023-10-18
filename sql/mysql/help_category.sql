create table mysql.help_category
(
    help_category_id   smallint unsigned not null
        primary key,
    name               char(64)          not null,
    parent_category_id smallint unsigned null,
    url                text              not null,
    constraint name
        unique (name)
)
    comment 'help categories' charset = utf8mb3;

