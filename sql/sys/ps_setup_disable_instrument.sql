create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_disable_instrument(IN in_pattern varchar(128)) comment '
Description
-----------

Disables instruments within Performance Schema 
matching the input pattern.

Parameters
-----------

in_pattern (VARCHAR(128)):
  A LIKE pattern match (using "%in_pattern%") of events to disable

Example
-----------

To disable all mutex instruments:

mysql> CALL sys.ps_setup_disable_instrument(''wait/synch/mutex'');
+--------------------------+
| summary                  |
+--------------------------+
| Disabled 155 instruments |
+--------------------------+
1 row in set (0.02 sec)

To disable just a specific TCP/IP based network IO instrument:

mysql> CALL sys.ps_setup_disable_instrument(''wait/io/socket/sql/server_tcpip_socket'');
+------------------------+
| summary                |
+------------------------+
| Disabled 1 instruments |
+------------------------+
1 row in set (0.00 sec)

To disable all instruments:

mysql> CALL sys.ps_setup_disable_instrument('''');
+--------------------------+
| summary                  |
+--------------------------+
| Disabled 547 instruments |
+--------------------------+
1 row in set (0.01 sec)
' sql security invoker modifies sql data
BEGIN
    UPDATE performance_schema.setup_instruments
       SET enabled = 'NO', timed = 'NO'
     WHERE name LIKE CONCAT('%', in_pattern, '%');
    SELECT CONCAT('Disabled ', @rows := ROW_COUNT(), ' instrument', IF(@rows != 1, 's', '')) AS summary;
END;

