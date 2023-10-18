create table fjh_001.usee_table
(
    id                  int         not null comment 'id '
        primary key,
    passward            int         null comment 'passward',
    name                varchar(20) null comment 'name ',
    schoolnumber        int         not null comment 'schoolnumber',
    age                 int         null comment 'age ',
    sex                 varchar(20) null comment 'sex',
    telephonenumber     int         null comment 'telephonenumber',
    post                int         null comment 'post',
    book__borrow_number int         null comment 'book__borrow_number ',
    book1_borrow        varchar(20) null comment 'book1_borrow',
    book2_borrow        varchar(20) null comment 'book2_borrow',
    book3_borrow        varchar(20) null comment 'book3_borrow'
);

