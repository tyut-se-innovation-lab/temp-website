create table mysql.ndb_binlog_index
(
    Position       bigint unsigned not null,
    File           varchar(255)    not null,
    epoch          bigint unsigned not null,
    inserts        int unsigned    not null,
    updates        int unsigned    not null,
    deletes        int unsigned    not null,
    schemaops      int unsigned    not null,
    orig_server_id int unsigned    not null,
    orig_epoch     bigint unsigned not null,
    gci            int unsigned    not null,
    next_position  bigint unsigned not null,
    next_file      varchar(255)    not null,
    primary key (epoch, orig_server_id, orig_epoch)
)
    charset = latin1;

