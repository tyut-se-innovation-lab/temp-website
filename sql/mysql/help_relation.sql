create table mysql.help_relation
(
    help_topic_id   int unsigned not null,
    help_keyword_id int unsigned not null,
    primary key (help_keyword_id, help_topic_id)
)
    comment 'keyword-topic relation' charset = utf8mb3;

