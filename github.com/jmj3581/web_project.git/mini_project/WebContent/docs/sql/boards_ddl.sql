/*
	## 게시판테이블 스키마 스크립트 파일
	-- 게시판테이블명 : boards
	-- 스크립트파일명 : boards_ddl.sql

	## 게시판 엔티티 분석 : 테이블명 BOARD
	1. 게시판 번호 : SEQ_BOARD_NO NUMBER(5) PRIMARY KEY
	2. 제목 : TITLE VARCHAR2(30) NOT NULL
	3. 아이디 : USER_ID VARCHAR2(20) NOT NULL
	4. 등록일 : REG_DATE VARCHAR2(15) NOT NULL
	5. 내용 : CONTENTS VARCHAR2(500) NOT NULL
	6. 조회수 : COUNT NUMBER(5) NOT NULL
	
*/
create sequence seq_board_no
	start with 1
	increment by 1
	maxvalue 99999
	minvalue 1
	nocycle;
	
create table boards(
	seq_board_no number(5) primary key,
	title varchar2(30) not null,
	user_id varchar2(20) not null,
	reg_date varchar2(15) not null,
	contents varchar2(500) not null,
	count number(5) not null
);