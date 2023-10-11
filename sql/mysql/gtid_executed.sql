create table mysql.gtid_executed
(
    source_uuid    char(36) not null comment 'uuid of the source where the transaction was originally executed.',
    interval_start bigint   not null comment 'First number of interval.',
    interval_end   bigint   not null comment 'Last number of interval.',
    primary key (source_uuid, interval_start)
);

