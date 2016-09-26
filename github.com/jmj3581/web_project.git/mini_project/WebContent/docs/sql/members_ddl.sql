/*
	## 회원테이블 스키마 스크립트 파일
	-- 회원테이블명 : members
	-- 스크립트파일명 : members_ddl.sql

	## 회원 엔티티 분석 : 테이블명 MEMBERS
	1. 아이디 : USER_ID VARCHAR2(20) PRIMARY KEY
	2. 비밀번호 : USER_PW VARCHAR2(20) NOT NULL
	3. 이름 : NAME VARCHAR2(20) NOT NULL
	4. 연락처 : MOBILE VARCHAR2(14) NOT NULL
	5. 이메일 : EMAIL VARCHAR2(20) NOT NULL
	6. 주소 : ADDRESS VARCHAR2(100)
	7. 방문횟수 : VISIT_COUNT NUMBER(5)
	8. 가입일 : ENTRY_DATE VARCHAR2(10)
	9. 등급 : GRADE VARCHAR2(1) NOT NULL
	
	## 속성 데이터
	-- 연락처 : 010-1234-1234, 011-123-1234
	-- 가입일 : 년도4자리/월2자리/일2자리
*/

create table members(
	user_id varchar2(20) primary key,
	user_pw varchar2(20) not null,
	name varchar2(20) not null,
	mobile varchar2(14) not null,
	email varchar2(20) not null,
	address varchar2(100),
	visit_count number(5),
	entry_date varchar2(10) not null,
	grade varchar2(1) not null
);