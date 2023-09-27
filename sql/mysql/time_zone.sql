create table mysql.time_zone
(
    Time_zone_id     int unsigned auto_increment
        primary key,
    Use_leap_seconds enum ('Y', 'N') default 'N' not null
)
    comment 'Time zones' charset = utf8mb3;

