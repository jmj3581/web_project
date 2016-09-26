/*
	## 게시판테이블에 대한 DML
	-- 테이블명 : boards
	-- 스크립트파일명 : boards_dml.sql

	-- 테이블 구조
 Name                    Null?    Type
 ----------------------- -------- ----------------
 SEQ_BOARD_NO            NOT NULL NUMBER(5)
 TITLE                   NOT NULL VARCHAR2(30)
 USER_ID                 NOT NULL VARCHAR2(20)
 REG_DATE                NOT NULL VARCHAR2(15)
 CONTENTS                NOT NULL VARCHAR2(500)
 COUNT                   NOT NULL NUMBER(5)
 
*/
-- 1. 등록기능
insert into boards
values(seq_board_no.nextval, '제목입니다.', '아이디', '등록일', '내용입니다.', 5);

-- 2. 삭제기능
delete from boards where board_no=1;

-- 3. 전체조회기능
select * from boards;

-- 4. 상세조회기능
select * from boards where board_no=1;

-- 5. 변경기능 
update boards set
title='제목', user_id='id', reg_date='2016/08/30', contents='contents' where board_no=1;