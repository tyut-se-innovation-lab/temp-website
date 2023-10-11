create table libary.user_book_borrow
(
    user_student_number        bigint    not null comment '拥有者id',
    book_id                    int       not null comment '图书id',
    borrow_user_student_number bigint    not null comment '借阅用户id',
    borrow_book_time           timestamp not null comment '借阅时间',
    return_book_time           datetime  not null comment '还书时间'
)
    charset = utf8mb3;

