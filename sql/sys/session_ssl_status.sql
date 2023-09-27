create definer = `mysql.sys`@localhost view sys.session_ssl_status as
select `sslver`.`THREAD_ID`        AS `thread_id`,
       `sslver`.`VARIABLE_VALUE`   AS `ssl_version`,
       `sslcip`.`VARIABLE_VALUE`   AS `ssl_cipher`,
       `sslreuse`.`VARIABLE_VALUE` AS `ssl_sessions_reused`
from ((`performance_schema`.`status_by_thread` `sslver` left join `performance_schema`.`status_by_thread` `sslcip`
       on (((`sslcip`.`THREAD_ID` = `sslver`.`THREAD_ID`) and
            (`sslcip`.`VARIABLE_NAME` = 'Ssl_cipher')))) left join `performance_schema`.`status_by_thread` `sslreuse`
      on (((`sslreuse`.`THREAD_ID` = `sslver`.`THREAD_ID`) and (`sslreuse`.`VARIABLE_NAME` = 'Ssl_sessions_reused'))))
where (`sslver`.`VARIABLE_NAME` = 'Ssl_version');

