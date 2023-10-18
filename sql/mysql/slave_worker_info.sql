create table mysql.slave_worker_info
(
    Id                         int unsigned             not null,
    Relay_log_name             text collate utf8mb3_bin not null,
    Relay_log_pos              bigint unsigned          not null,
    Master_log_name            text collate utf8mb3_bin not null,
    Master_log_pos             bigint unsigned          not null,
    Checkpoint_relay_log_name  text collate utf8mb3_bin not null,
    Checkpoint_relay_log_pos   bigint unsigned          not null,
    Checkpoint_master_log_name text collate utf8mb3_bin not null,
    Checkpoint_master_log_pos  bigint unsigned          not null,
    Checkpoint_seqno           int unsigned             not null,
    Checkpoint_group_size      int unsigned             not null,
    Checkpoint_group_bitmap    blob                     not null,
    Channel_name               varchar(64)              not null comment 'The channel on which the replica is connected to a source. Used in Multisource Replication',
    primary key (Channel_name, Id)
)
    comment 'Worker Information' charset = utf8mb3;

