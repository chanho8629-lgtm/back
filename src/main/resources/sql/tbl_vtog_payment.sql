# 18
create table tbl_vtog_payment
(#VtoG거래내역
                          id                       bigint unsigned auto_increment primary key,
 vtog_payment_sales_kwh   bigint          not null,
 vtog_payment_sales_price bigint          not null,
 created_date             datetime default current_timestamp(),
 updated_date             datetime default current_timestamp(),
 vtog_car_id              bigint unsigned not null,
 constraint fk_vtog_car_id foreign key (vtog_car_id) references tbl_car (id)
);