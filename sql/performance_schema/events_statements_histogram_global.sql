create table performance_schema.events_statements_histogram_global
(
    BUCKET_NUMBER          int unsigned    not null
        primary key using hash,
    BUCKET_TIMER_LOW       bigint unsigned not null,
    BUCKET_TIMER_HIGH      bigint unsigned not null,
    COUNT_BUCKET           bigint unsigned not null,
    COUNT_BUCKET_AND_LOWER bigint unsigned not null,
    BUCKET_QUANTILE        double(7, 6)    not null
)
    engine = PERFORMANCE_SCHEMA;

