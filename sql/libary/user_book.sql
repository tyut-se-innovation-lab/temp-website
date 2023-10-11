create table libary.user_book
(
    book_id             int auto_increment comment '图书编号'
        primary key,
    book_name           varchar(30) not null comment '图书名称',
    user_student_number bigint      not null comment '用户id',
    book_status         int         not null comment '借阅状态',
    book_price          double      not null comment '图书价格'
)
    charset = utf8mb3;

