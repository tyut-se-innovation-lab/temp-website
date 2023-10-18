create table performance_schema.events_statements_histogram_by_digest
(
    SCHEMA_NAME            varchar(64)     null,
    DIGEST                 varchar(64)     null,
    BUCKET_NUMBER          int unsigned    not null,
    BUCKET_TIMER_LOW       bigint unsigned not null,
    BUCKET_TIMER_HIGH      bigint unsigned not null,
    COUNT_BUCKET           bigint unsigned not null,
    COUNT_BUCKET_AND_LOWER bigint unsigned not null,
    BUCKET_QUANTILE        double(7, 6)    not null,
    constraint SCHEMA_NAME
        unique (SCHEMA_NAME, DIGEST, BUCKET_NUMBER) using hash
)
    engine = PERFORMANCE_SCHEMA;

