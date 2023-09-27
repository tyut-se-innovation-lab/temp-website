create table fjh_001.tb_brand
(
    id           int auto_increment
        primary key,
    brand_name   varchar(20)  null,
    company_name varchar(20)  null,
    ordered      int          null,
    description  varchar(100) null,
    status       int          null
);

