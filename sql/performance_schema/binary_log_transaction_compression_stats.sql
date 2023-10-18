create table performance_schema.binary_log_transaction_compression_stats
(
    LOG_TYPE                             enum ('BINARY', 'RELAY') not null comment 'The log type to which the transactions were written.',
    COMPRESSION_TYPE                     varchar(64)              not null comment 'The transaction compression algorithm used.',
    TRANSACTION_COUNTER                  bigint unsigned          not null comment 'Number of transactions written to the log',
    COMPRESSED_BYTES_COUNTER             bigint unsigned          not null comment 'The total number of bytes compressed.',
    UNCOMPRESSED_BYTES_COUNTER           bigint unsigned          not null comment 'The total number of bytes uncompressed.',
    COMPRESSION_PERCENTAGE               smallint                 not null comment 'The compression ratio as a percentage.',
    FIRST_TRANSACTION_ID                 text                     null comment 'The first transaction written.',
    FIRST_TRANSACTION_COMPRESSED_BYTES   bigint unsigned          not null comment 'First transaction written compressed bytes.',
    FIRST_TRANSACTION_UNCOMPRESSED_BYTES bigint unsigned          not null comment 'First transaction written uncompressed bytes.',
    FIRST_TRANSACTION_TIMESTAMP          timestamp(6)             null comment 'When the first transaction was written.',
    LAST_TRANSACTION_ID                  text                     null comment 'The last transaction written.',
    LAST_TRANSACTION_COMPRESSED_BYTES    bigint unsigned          not null comment 'Last transaction written compressed bytes.',
    LAST_TRANSACTION_UNCOMPRESSED_BYTES  bigint unsigned          not null comment 'Last transaction written uncompressed bytes.',
    LAST_TRANSACTION_TIMESTAMP           timestamp(6)             null comment 'When the last transaction was written.'
)
    engine = PERFORMANCE_SCHEMA;

