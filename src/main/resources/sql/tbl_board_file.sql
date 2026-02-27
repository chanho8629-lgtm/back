# 21
create table tbl_board_file
( #파일
           id        bigint unsigned primary key,
  board_id bigint unsigned not null,
  constraint fk_board_file_to_file_id foreign key (id) references tbl_file (id),
  constraint fk_board_file_board_id foreign key (board_id) references tbl_board (id)
);

select * from tbl_board_file;

drop table tbl_board_file;

select * from view_board_file;
delete from tbl_post_file;

create view view_board_file as
(
select f.id,
       file_path,
       file_name,
       file_original_name,
       file_size,
       file_content_type,
       created_date,
       updated_date,
       board_id
from tbl_file f
         join tbl_board_file bf
              on f.id = bf.id
    );
