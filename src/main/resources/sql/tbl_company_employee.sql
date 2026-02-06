# 17
create table tbl_company_employee
( #직원
                              id                          bigint unsigned auto_increment primary key,
  company_employee_email      varchar(255)    not null,
  company_employee_number     varchar(255)    not null,
  company_employee_department varchar(255)    not null,
  company_employee_status     enum ('enable', 'disable') default 'enable',
  created_date                datetime                   default current_timestamp(),
  updated_date                datetime                   default current_timestamp(),
  employee_company_id         bigint unsigned not null,
  constraint fk_employee_company_id foreign key (employee_company_id) references tbl_company (id)
);