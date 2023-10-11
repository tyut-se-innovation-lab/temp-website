create table mysql.help_keyword
(
    help_keyword_id int unsigned not null
        primary key,
    name            char(64)     not null,
    constraint name
        unique (name)
)
    comment 'help keywords' charset = utf8mb3;

