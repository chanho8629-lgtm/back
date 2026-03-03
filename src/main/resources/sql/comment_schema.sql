create table if not exists tbl_comment
(
    id                bigint unsigned auto_increment primary key,
    comment_content   varchar(255)                 not null,
    comment_status    enum ('active', 'inactive')  default 'active',
    created_date      datetime                     default current_timestamp(),
    updated_date      datetime                     default current_timestamp() on update current_timestamp(),
    comment_board_id  bigint unsigned              not null,
    comment_member_id bigint unsigned              not null,
    constraint fk_comment_board_id foreign key (comment_board_id) references tbl_board (id),
    constraint fk_comment_member_id foreign key (comment_member_id) references tbl_member (id)
);
