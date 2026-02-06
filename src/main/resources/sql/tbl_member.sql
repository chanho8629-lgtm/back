# 1
CREATE TABLE tbl_member
(
    id              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    member_email    VARCHAR(255) NOT NULL UNIQUE, -- 중복 방지
    member_password VARCHAR(255),                 -- 소셜 전용 유저는 NULL 가능
    member_name     VARCHAR(255) NOT NULL,
    member_birth    varchar(255),
    member_address  VARCHAR(255),                 -- 소셜 가입 시 주소는 나중에 받을 수도 있음
    member_filter   ENUM ('individual', 'company') DEFAULT 'individual',
    member_status   ENUM ('enable', 'disable')     DEFAULT 'enable',
    created_date    DATETIME                       DEFAULT CURRENT_TIMESTAMP(),
    updated_date    DATETIME                       DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()
);