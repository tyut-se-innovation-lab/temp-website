create table performance_schema.innodb_redo_log_files
(
    FILE_ID        bigint        not null comment 'Id of the file.',
    FILE_NAME      varchar(2000) not null comment 'Path to the file.',
    START_LSN      bigint        not null comment 'LSN of the first block in the file.',
    END_LSN        bigint        not null comment 'LSN after the last block in the file.',
    SIZE_IN_BYTES  bigint        not null comment 'Size of the file (in bytes).',
    IS_FULL        tinyint       not null comment '1 iff file has no free space inside.',
    CONSUMER_LEVEL int           not null comment 'All redo log consumers registered on smaller levels than this value, have already consumed this file.'
)
    engine = PERFORMANCE_SCHEMA;

