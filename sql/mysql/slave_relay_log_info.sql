create table mysql.slave_relay_log_info
(
    Number_of_lines                              int unsigned                                              not null comment 'Number of lines in the file or rows in the table. Used to version table definitions.',
    Relay_log_name                               text collate utf8mb3_bin                                  null comment 'The name of the current relay log file.',
    Relay_log_pos                                bigint unsigned                                           null comment 'The relay log position of the last executed event.',
    Master_log_name                              text collate utf8mb3_bin                                  null comment 'The name of the master binary log file from which the events in the relay log file were read.',
    Master_log_pos                               bigint unsigned                                           null comment 'The master log position of the last executed event.',
    Sql_delay                                    int                                                       null comment 'The number of seconds that the slave must lag behind the master.',
    Number_of_workers                            int unsigned                                              null,
    Id                                           int unsigned                                              null comment 'Internal Id that uniquely identifies this record.',
    Channel_name                                 varchar(64)                                               not null comment 'The channel on which the replica is connected to a source. Used in Multisource Replication'
        primary key,
    Privilege_checks_username                    varchar(32) collate utf8mb3_bin                           null comment 'Username part of PRIVILEGE_CHECKS_USER.',
    Privilege_checks_hostname                    varchar(255) charset ascii                                null comment 'Hostname part of PRIVILEGE_CHECKS_USER.',
    Require_row_format                           tinyint(1)                                                not null comment 'Indicates whether the channel shall only accept row based events.',
    Require_table_primary_key_check              enum ('STREAM', 'ON', 'OFF', 'GENERATE') default 'STREAM' not null comment 'Indicates what is the channel policy regarding tables without primary keys on create and alter table queries',
    Assign_gtids_to_anonymous_transactions_type  enum ('OFF', 'LOCAL', 'UUID')            default 'OFF'    not null comment 'Indicates whether the channel will generate a new GTID for anonymous transactions. OFF means that anonymous transactions will remain anonymous. LOCAL means that anonymous transactions will be assigned a newly generated GTID based on server_uuid. UUID indicates that anonymous transactions will be assigned a newly generated GTID based on Assign_gtids_to_anonymous_transactions_value',
    Assign_gtids_to_anonymous_transactions_value text collate utf8mb3_bin                                  null comment 'Indicates the UUID used while generating GTIDs for anonymous transactions'
)
    comment 'Relay Log Information' charset = utf8mb3;

