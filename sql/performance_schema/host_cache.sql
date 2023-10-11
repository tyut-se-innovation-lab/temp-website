create table performance_schema.host_cache
(
    IP                                         varchar(64)                not null
        primary key using hash,
    HOST                                       varchar(255) charset ascii null,
    HOST_VALIDATED                             enum ('YES', 'NO')         not null,
    SUM_CONNECT_ERRORS                         bigint                     not null,
    COUNT_HOST_BLOCKED_ERRORS                  bigint                     not null,
    COUNT_NAMEINFO_TRANSIENT_ERRORS            bigint                     not null,
    COUNT_NAMEINFO_PERMANENT_ERRORS            bigint                     not null,
    COUNT_FORMAT_ERRORS                        bigint                     not null,
    COUNT_ADDRINFO_TRANSIENT_ERRORS            bigint                     not null,
    COUNT_ADDRINFO_PERMANENT_ERRORS            bigint                     not null,
    COUNT_FCRDNS_ERRORS                        bigint                     not null,
    COUNT_HOST_ACL_ERRORS                      bigint                     not null,
    COUNT_NO_AUTH_PLUGIN_ERRORS                bigint                     not null,
    COUNT_AUTH_PLUGIN_ERRORS                   bigint                     not null,
    COUNT_HANDSHAKE_ERRORS                     bigint                     not null,
    COUNT_PROXY_USER_ERRORS                    bigint                     not null,
    COUNT_PROXY_USER_ACL_ERRORS                bigint                     not null,
    COUNT_AUTHENTICATION_ERRORS                bigint                     not null,
    COUNT_SSL_ERRORS                           bigint                     not null,
    COUNT_MAX_USER_CONNECTIONS_ERRORS          bigint                     not null,
    COUNT_MAX_USER_CONNECTIONS_PER_HOUR_ERRORS bigint                     not null,
    COUNT_DEFAULT_DATABASE_ERRORS              bigint                     not null,
    COUNT_INIT_CONNECT_ERRORS                  bigint                     not null,
    COUNT_LOCAL_ERRORS                         bigint                     not null,
    COUNT_UNKNOWN_ERRORS                       bigint                     not null,
    FIRST_SEEN                                 timestamp                  not null,
    LAST_SEEN                                  timestamp                  not null,
    FIRST_ERROR_SEEN                           timestamp                  null,
    LAST_ERROR_SEEN                            timestamp                  null
)
    engine = PERFORMANCE_SCHEMA;

create index HOST
    on performance_schema.host_cache (HOST)
    using hash;

