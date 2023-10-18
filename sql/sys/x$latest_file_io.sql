create definer = `mysql.sys`@localhost view sys.x$latest_file_io as
select if((`information_schema`.`processlist`.`ID` is null),
          concat(substring_index(`performance_schema`.`threads`.`NAME`, '/', -(1)), ':',
                 `performance_schema`.`events_waits_history_long`.`THREAD_ID`), convert(
                  concat(`information_schema`.`processlist`.`USER`, '@', `information_schema`.`processlist`.`HOST`, ':',
                         `information_schema`.`processlist`.`ID`) using utf8mb4)) AS `thread`,
       `performance_schema`.`events_waits_history_long`.`OBJECT_NAME`             AS `file`,
       `performance_schema`.`events_waits_history_long`.`TIMER_WAIT`              AS `latency`,
       `performance_schema`.`events_waits_history_long`.`OPERATION`               AS `operation`,
       `performance_schema`.`events_waits_history_long`.`NUMBER_OF_BYTES`         AS `requested`
from ((`performance_schema`.`events_waits_history_long` join `performance_schema`.`threads`
       on ((`performance_schema`.`events_waits_history_long`.`THREAD_ID` =
            `performance_schema`.`threads`.`THREAD_ID`))) left join `information_schema`.`PROCESSLIST`
      on ((`performance_schema`.`threads`.`PROCESSLIST_ID` = `information_schema`.`processlist`.`ID`)))
where ((`performance_schema`.`events_waits_history_long`.`OBJECT_NAME` is not null) and
       (`performance_schema`.`events_waits_history_long`.`EVENT_NAME` like 'wait/io/file/%'))
order by `performance_schema`.`events_waits_history_long`.`TIMER_START`;

