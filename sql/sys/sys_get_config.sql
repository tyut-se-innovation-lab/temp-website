create
    definer = `mysql.sys`@localhost function sys.sys_get_config(in_variable_name varchar(128), in_default_value varchar(128)) returns varchar(128)
    comment '
Description
-----------

Returns the value for the requested variable using the following logic:

   1. If the option exists in sys.sys_config return the value from there.
   2. Else fall back on the provided default value.

Notes for using sys_get_config():

   * If the default value argument to sys_get_config() is NULL and case 2. is reached, NULL is returned.
     It is then expected that the caller is able to handle NULL for the given configuration option.
   * The convention is to name the user variables @sys.<name of variable>. It is <name of variable> that
     is stored in the sys_config table and is what is expected as the argument to sys_get_config().
   * If you want to check whether the configuration option has already been set and if not assign with
     the return value of sys_get_config() you can use IFNULL(...) (see example below). However this should
     not be done inside a loop (e.g. for each row in a result set) as for repeated calls where assignment
     is only needed in the first iteration using IFNULL(...) is expected to be significantly slower than
     using an IF (...) THEN ... END IF; block (see example below).

Parameters
-----------

in_variable_name (VARCHAR(128)):
  The name of the config option to return the value for.

in_default_value (VARCHAR(128)):
  The default value to return if the variable does not exist in sys.sys_config.

Returns
-----------

VARCHAR(128)

Example
-----------

-- Get the configuration value from sys.sys_config falling back on 128 if the option is not present in the table.
mysql> SELECT sys.sys_get_config(''statement_truncate_len'', 128) AS Value;
+-------+
| Value |
+-------+
| 64    |
+-------+
1 row in set (0.00 sec)

-- Check whether the option is already set, if not assign - IFNULL(...) one liner example.
mysql> SET @sys.statement_truncate_len = IFNULL(@sys.statement_truncate_len, sys.sys_get_config(''statement_truncate_len'', 64));
Query OK, 0 rows affected (0.00 sec)

-- Check whether the option is already set, if not assign - IF ... THEN ... END IF example.
IF (@sys.statement_truncate_len IS NULL) THEN
    SET @sys.statement_truncate_len = sys.sys_get_config(''statement_truncate_len'', 64);
END IF;
'
    deterministic
    sql security invoker
    reads sql data
BEGIN
    DECLARE v_value VARCHAR(128) DEFAULT NULL;
    -- Check if we have the variable in the sys.sys_config table
    SET v_value = (SELECT value FROM sys.sys_config WHERE variable = in_variable_name);
    -- Protection against the variable not existing in sys_config
    IF (v_value IS NULL) THEN
        SET v_value = in_default_value;
    END IF;
    RETURN v_value;
END;

