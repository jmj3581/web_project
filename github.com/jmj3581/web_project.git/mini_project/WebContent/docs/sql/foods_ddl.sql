/*
	## 음식점테이블 스키마 스크립트 파일
	-- 음식점테이블명 : foods
	-- 스크립트파일명 : foods_ddl.sql

	## 회원 엔티티 분석 : 테이블명 FOODS
	1. 음식점번호 : FOOD_NO NUMBER(5) PRIMARY KEY
	2. 업종 : KIND VARCHAR2(50) NOT NULL
	3. 나라 : COUNTRY VARCHAR2(30) NOT NULL
	4. 음식점명 : STORE_NAME VARCHAR2(30) NOT NULL
	5. 주소 : ADDRESS VARCHAR2(100) NOT NULL
	6. 홈페이지주소 : HOMEPAGE VARCHAR2(70)
	7. 음식점전화번호 : PHONE_NUMBER VARCHAR2(15)
	8. 파일명 : FILE_NAME VARCHAR2(50)
	
*/

/*
create sequence seq_food_no
	start with 1
	increment by 1
	maxvalue 99999
	minvalue 1
	nocycle;
*/

create table foods(
	food_no number(5) primary key,
	kind varchar2(50) not null,
	country varchar2(30) not null,
	store_name varchar2(30) not null,
	address varchar2(100) not null,
	homepage varchar2(70),
	phone_number varchar2(15),
	file_name varchar2(50)
);