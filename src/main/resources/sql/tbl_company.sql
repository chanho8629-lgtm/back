# 15
create table tbl_company
(#기업관리자
                  id               bigint unsigned auto_increment primary key,
 company_name     varchar(255)               default 'admin',
 company_email    varchar(255) not null,
 company_password varchar(255) not null,
 company_address  varchar(255) not null,
 company_status   enum ('enable', 'disable') default 'enable',
 created_date     datetime                   default current_timestamp(),
 updated_date     datetime                   default current_timestamp()
);