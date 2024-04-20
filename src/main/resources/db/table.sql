create table product_tb (
        id integer not null auto_increment,
        price integer not null,
        qty integer not null,
        created_at datetime(6),
        name varchar(20) not null,
        primary key (id)
    );

-- 판매자 상품 테이블
insert into product_tb(id, name, price, qty, created_at)
values
    (1, '바나나', 5000, 98, now()),
    (2, '딸기', 5000, 98, now()),
    (3, '수박', 5000, 98, now()),
    (4, '오렌지', 5000, 98, now()),
    (5, '메론', 5000, 98, now()),
    (6, '복숭아', 5000, 98, now());