create definer = `mysql.sys`@localhost view sys.version as
select '2.1.2' AS `sys_version`, version() AS `mysql_version`;

