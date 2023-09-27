create
    definer = `mysql.sys`@localhost function sys.quote_identifier(in_identifier text) returns text comment '
Description
-----------

Takes an unquoted identifier (schema name, table name, etc.) and
returns the identifier quoted with backticks.

Parameters
-----------

in_identifier (TEXT):
  The identifier to quote.

Returns
-----------

TEXT CHARSET UTF8MB4

Example
-----------

mysql> SELECT sys.quote_identifier(''my_identifier'') AS Identifier;
+-----------------+
| Identifier      |
+-----------------+
| `my_identifier` |
+-----------------+
1 row in set (0.00 sec)

mysql> SELECT sys.quote_identifier(''my`idenfier'') AS Identifier;
+----------------+
| Identifier     |
+----------------+
| `my``idenfier` |
+----------------+
1 row in set (0.00 sec)
' deterministic sql security invoker no sql
BEGIN
    RETURN CONCAT('`', REPLACE(in_identifier, '`', '``'), '`');
END;

