# 16
create table tbl_ev_charger
( #EV충전기
                     id                 bigint unsigned auto_increment primary key,
  ev_charger_address varchar(255)    not null,
  ev_charger_status  enum ('enable', 'disable') default 'enable',
  ev_charger_mod     enum ('enable', 'disable') default 'enable',
  created_date       datetime                   default current_timestamp(),
  updated_date       datetime                   default current_timestamp(),
  company_id         bigint unsigned not null,
  constraint fk_ev_charger_company_id foreign key (company_id) references tbl_company (id)
);