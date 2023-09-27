create table mysql.slave_master_info
(
    Number_of_lines                 int unsigned                    not null comment 'Number of lines in the file.',
    Master_log_name                 text collate utf8mb3_bin        not null comment 'The name of the master binary log currently being read from the master.',
    Master_log_pos                  bigint unsigned                 not null comment 'The master log position of the last read event.',
    Host                            varchar(255) charset ascii      null comment 'The host name of the source.',
    User_name                       text collate utf8mb3_bin        null comment 'The user name used to connect to the master.',
    User_password                   text collate utf8mb3_bin        null comment 'The password used to connect to the master.',
    Port                            int unsigned                    not null comment 'The network port used to connect to the master.',
    Connect_retry                   int unsigned                    not null comment 'The period (in seconds) that the slave will wait before trying to reconnect to the master.',
    Enabled_ssl                     tinyint(1)                      not null comment 'Indicates whether the server supports SSL connections.',
    Ssl_ca                          text collate utf8mb3_bin        null comment 'The file used for the Certificate Authority (CA) certificate.',
    Ssl_capath                      text collate utf8mb3_bin        null comment 'The path to the Certificate Authority (CA) certificates.',
    Ssl_cert                        text collate utf8mb3_bin        null comment 'The name of the SSL certificate file.',
    Ssl_cipher                      text collate utf8mb3_bin        null comment 'The name of the cipher in use for the SSL connection.',
    Ssl_key                         text collate utf8mb3_bin        null comment 'The name of the SSL key file.',
    Ssl_verify_server_cert          tinyint(1)                      not null comment 'Whether to verify the server certificate.',
    Heartbeat                       float                           not null,
    Bind                            text collate utf8mb3_bin        null comment 'Displays which interface is employed when connecting to the MySQL server',
    Ignored_server_ids              text collate utf8mb3_bin        null comment 'The number of server IDs to be ignored, followed by the actual server IDs',
    Uuid                            text collate utf8mb3_bin        null comment 'The master server uuid.',
    Retry_count                     bigint unsigned                 not null comment 'Number of reconnect attempts, to the master, before giving up.',
    Ssl_crl                         text collate utf8mb3_bin        null comment 'The file used for the Certificate Revocation List (CRL)',
    Ssl_crlpath                     text collate utf8mb3_bin        null comment 'The path used for Certificate Revocation List (CRL) files',
    Enabled_auto_position           tinyint(1)                      not null comment 'Indicates whether GTIDs will be used to retrieve events from the master.',
    Channel_name                    varchar(64)                     not null comment 'The channel on which the replica is connected to a source. Used in Multisource Replication'
        primary key,
    Tls_version                     text collate utf8mb3_bin        null comment 'Tls version',
    Public_key_path                 text collate utf8mb3_bin        null comment 'The file containing public key of master server.',
    Get_public_key                  tinyint(1)                      not null comment 'Preference to get public key from master.',
    Network_namespace               text collate utf8mb3_bin        null comment 'Network namespace used for communication with the master server.',
    Master_compression_algorithm    varchar(64) collate utf8mb3_bin not null comment 'Compression algorithm supported for data transfer between source and replica.',
    Master_zstd_compression_level   int unsigned                    not null comment 'Compression level associated with zstd compression algorithm.',
    Tls_ciphersuites                text collate utf8mb3_bin        null comment 'Ciphersuites used for TLS 1.3 communication with the master server.',
    Source_connection_auto_failover tinyint(1) default 0            not null comment 'Indicates whether the channel connection failover is enabled.',
    Gtid_only                       tinyint(1) default 0            not null comment 'Indicates if this channel only uses GTIDs and does not persist positions.'
)
    comment 'Master Information' charset = utf8mb3;

