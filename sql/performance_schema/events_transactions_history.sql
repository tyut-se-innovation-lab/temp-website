create table performance_schema.events_transactions_history
(
    THREAD_ID                       bigint unsigned                                    not null,
    EVENT_ID                        bigint unsigned                                    not null,
    END_EVENT_ID                    bigint unsigned                                    null,
    EVENT_NAME                      varchar(128)                                       not null,
    STATE                           enum ('ACTIVE', 'COMMITTED', 'ROLLED BACK')        null,
    TRX_ID                          bigint unsigned                                    null,
    GTID                            varchar(64)                                        null,
    XID_FORMAT_ID                   int                                                null,
    XID_GTRID                       varchar(130)                                       null,
    XID_BQUAL                       varchar(130)                                       null,
    XA_STATE                        varchar(64)                                        null,
    SOURCE                          varchar(64)                                        null,
    TIMER_START                     bigint unsigned                                    null,
    TIMER_END                       bigint unsigned                                    null,
    TIMER_WAIT                      bigint unsigned                                    null,
    ACCESS_MODE                     enum ('READ ONLY', 'READ WRITE')                   null,
    ISOLATION_LEVEL                 varchar(64)                                        null,
    AUTOCOMMIT                      enum ('YES', 'NO')                                 not null,
    NUMBER_OF_SAVEPOINTS            bigint unsigned                                    null,
    NUMBER_OF_ROLLBACK_TO_SAVEPOINT bigint unsigned                                    null,
    NUMBER_OF_RELEASE_SAVEPOINT     bigint unsigned                                    null,
    OBJECT_INSTANCE_BEGIN           bigint unsigned                                    null,
    NESTING_EVENT_ID                bigint unsigned                                    null,
    NESTING_EVENT_TYPE              enum ('TRANSACTION', 'STATEMENT', 'STAGE', 'WAIT') null,
    primary key (THREAD_ID, EVENT_ID) using hash
)
    engine = PERFORMANCE_SCHEMA;

