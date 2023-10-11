create definer = `mysql.sys`@localhost trigger sys.sys_config_insert_set_user
    before insert
    on sys.sys_config
    for each row
BEGIN
    IF @sys.ignore_sys_config_triggers != true AND NEW.set_by IS NULL THEN
        SET NEW.set_by = USER();
    END IF;
END;

