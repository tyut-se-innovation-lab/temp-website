create
    definer = `mysql.sys`@localhost procedure sys.ps_setup_enable_consumer(IN consumer varchar(128)) comment '
Description
-----------

Enables consumers within Performance Schema 
matching the input pattern.

Parameters
-----------

consumer (VARCHAR(128)):
  A LIKE pattern match (using "%consumer%") of consumers to enable

Example
-----------

To enable all consumers:

mysql> CALL sys.ps_setup_enable_consumer('''');
+-------------------------+
| summary                 |
+-------------------------+
| Enabled 10 consumers    |
+-------------------------+
1 row in set (0.02 sec)

Query OK, 0 rows affected (0.02 sec)

To enable just "waits" consumers:

mysql> CALL sys.ps_setup_enable_consumer(''waits'');
+-----------------------+
| summary               |
+-----------------------+
| Enabled 3 consumers   |
+-----------------------+
1 row in set (0.00 sec)

Query OK, 0 rows affected (0.00 sec)
' sql security invoker modifies sql data
BEGIN
    UPDATE performance_schema.setup_consumers
       SET enabled = 'YES'
     WHERE name LIKE CONCAT('%', consumer, '%');
    SELECT CONCAT('Enabled ', @rows := ROW_COUNT(), ' consumer', IF(@rows != 1, 's', '')) AS summary;
END;

