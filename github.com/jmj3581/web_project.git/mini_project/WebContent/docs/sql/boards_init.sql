/*
	## 게시판테이블에 대한 DML
	-- 테이블명 : boards
	-- 스크립트파일명 : boards_init.sql

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

insert into boards
values(seq_board_no.nextval, '제목입니다.', '아이디', '2016/08/31', '내용입니다.', 5);

insert into boards
values(seq_board_no.nextval, '제목입니다.', 'test01', '2016/08/30', '내용입니다.', 5);

insert into boards
values(seq_board_no.nextval, '제목입니다.', 'test02', '2016/08/06', '내용입니다.', 5);