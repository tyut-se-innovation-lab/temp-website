create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_enable_instrument(IN in_pattern varchar(128)) comment '
Description
-----------

Enables instruments within Performance Schema 
matching the input pattern.

Parameters
-----------

in_pattern (VARCHAR(128)):
  A LIKE pattern match (using "%in_pattern%") of events to enable

Example
-----------

To enable all mutex instruments:

mysql> CALL sys.ps_setup_enable_instrument(''wait/synch/mutex'');
+-------------------------+
| summary                 |
+-------------------------+
| Enabled 155 instruments |
+-------------------------+
1 row in set (0.02 sec)

Query OK, 0 rows affected (0.02 sec)

To enable just a specific TCP/IP based network IO instrument:

mysql> CALL sys.ps_setup_enable_instrument(''wait/io/socket/sql/server_tcpip_socket'');
+-----------------------+
| summary               |
+-----------------------+
| Enabled 1 instruments |
+-----------------------+
1 row in set (0.00 sec)

Query OK, 0 rows affected (0.00 sec)

To enable all instruments:

mysql> CALL sys.ps_setup_enable_instrument('''');
+-------------------------+
| summary                 |
+-------------------------+
| Enabled 547 instruments |
+-------------------------+
1 row in set (0.01 sec)

Query OK, 0 rows affected (0.01 sec)
' sql security invoker modifies sql data
BEGIN
    UPDATE performance_schema.setup_instruments
       SET enabled = 'YES', timed = 'YES'
     WHERE name LIKE CONCAT('%', in_pattern, '%');
    SELECT CONCAT('Enabled ', @rows := ROW_COUNT(), ' instrument', IF(@rows != 1, 's', '')) AS summary;
END;

