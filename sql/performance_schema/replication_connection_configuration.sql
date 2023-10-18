create table performance_schema.replication_connection_configuration
(
    CHANNEL_NAME                    char(64)                      not null
        primary key using hash,
    HOST                            char(255) charset ascii       not null,
    PORT                            int                           not null,
    USER                            char(32) collate utf8mb4_bin  not null,
    NETWORK_INTERFACE               char(60) collate utf8mb4_bin  not null,
    AUTO_POSITION                   enum ('1', '0')               not null,
    SSL_ALLOWED                     enum ('YES', 'NO', 'IGNORED') not null,
    SSL_CA_FILE                     varchar(512)                  not null,
    SSL_CA_PATH                     varchar(512)                  not null,
    SSL_CERTIFICATE                 varchar(512)                  not null,
    SSL_CIPHER                      varchar(512)                  not null,
    SSL_KEY                         varchar(512)                  not null,
    SSL_VERIFY_SERVER_CERTIFICATE   enum ('YES', 'NO')            not null,
    SSL_CRL_FILE                    varchar(255)                  not null,
    SSL_CRL_PATH                    varchar(255)                  not null,
    CONNECTION_RETRY_INTERVAL       int                           not null,
    CONNECTION_RETRY_COUNT          bigint unsigned               not null,
    HEARTBEAT_INTERVAL              double(10, 3)                 not null comment 'Number of seconds after which a heartbeat will be sent .',
    TLS_VERSION                     varchar(255)                  not null,
    PUBLIC_KEY_PATH                 varchar(512)                  not null,
    GET_PUBLIC_KEY                  enum ('YES', 'NO')            not null,
    NETWORK_NAMESPACE               varchar(64)                   not null,
    COMPRESSION_ALGORITHM           char(64) collate utf8mb4_bin  not null comment 'Compression algorithm used for data transfer between master and slave.',
    ZSTD_COMPRESSION_LEVEL          int                           not null comment 'Compression level associated with zstd compression algorithm.',
    TLS_CIPHERSUITES                text collate utf8mb3_bin      null,
    SOURCE_CONNECTION_AUTO_FAILOVER enum ('1', '0')               not null,
    GTID_ONLY                       enum ('1', '0')               not null comment 'Indicates if this channel only uses GTIDs and does not persist positions.'
)
    engine = PERFORMANCE_SCHEMA;

