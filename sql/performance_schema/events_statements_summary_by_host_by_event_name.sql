create table performance_schema.events_statements_summary_by_host_by_event_name
(
    HOST                        char(255) charset ascii null,
    EVENT_NAME                  varchar(128)            not null,
    COUNT_STAR                  bigint unsigned         not null,
    SUM_TIMER_WAIT              bigint unsigned         not null,
    MIN_TIMER_WAIT              bigint unsigned         not null,
    AVG_TIMER_WAIT              bigint unsigned         not null,
    MAX_TIMER_WAIT              bigint unsigned         not null,
    SUM_LOCK_TIME               bigint unsigned         not null,
    SUM_ERRORS                  bigint unsigned         not null,
    SUM_WARNINGS                bigint unsigned         not null,
    SUM_ROWS_AFFECTED           bigint unsigned         not null,
    SUM_ROWS_SENT               bigint unsigned         not null,
    SUM_ROWS_EXAMINED           bigint unsigned         not null,
    SUM_CREATED_TMP_DISK_TABLES bigint unsigned         not null,
    SUM_CREATED_TMP_TABLES      bigint unsigned         not null,
    SUM_SELECT_FULL_JOIN        bigint unsigned         not null,
    SUM_SELECT_FULL_RANGE_JOIN  bigint unsigned         not null,
    SUM_SELECT_RANGE            bigint unsigned         not null,
    SUM_SELECT_RANGE_CHECK      bigint unsigned         not null,
    SUM_SELECT_SCAN             bigint unsigned         not null,
    SUM_SORT_MERGE_PASSES       bigint unsigned         not null,
    SUM_SORT_RANGE              bigint unsigned         not null,
    SUM_SORT_ROWS               bigint unsigned         not null,
    SUM_SORT_SCAN               bigint unsigned         not null,
    SUM_NO_INDEX_USED           bigint unsigned         not null,
    SUM_NO_GOOD_INDEX_USED      bigint unsigned         not null,
    SUM_CPU_TIME                bigint unsigned         not null,
    MAX_CONTROLLED_MEMORY       bigint unsigned         not null,
    MAX_TOTAL_MEMORY            bigint unsigned         not null,
    COUNT_SECONDARY             bigint unsigned         not null,
    constraint HOST
        unique (HOST, EVENT_NAME) using hash
)
    engine = PERFORMANCE_SCHEMA;

