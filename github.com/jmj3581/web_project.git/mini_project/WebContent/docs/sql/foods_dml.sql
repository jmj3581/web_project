/*
	## 음식점테이블에 대한 DML
	-- 테이블명 : foods
	-- 스크립트파일명 : foods_dml.sql

	-- 테이블 구조

 Name                    Null?    Type
 ----------------------- -------- ----------------
 FOOD_NO                 NOT NULL NUMBER(5)
 KIND                    NOT NULL VARCHAR2(50)
 COUNTRY                 NOT NULL VARCHAR2(30)
 STORE_NAME              NOT NULL VARCHAR2(30)
 ADDRESS                 NOT NULL VARCHAR2(100)
 HOMEPAGE                         VARCHAR2(70)
 PHONE_NUMBER                     VARCHAR2(15)
 FILE_NAME                        VARCHAR2(50)
*/

--1. 등록기능
insert into foods
values(1, '양식', '브라질', '텍사스 데 브라질', '서울특별시 서초구 반포동 118-3', 'http://www.texasdebrazil.com/','02-6282-5000', 'sa_01.jpg');

--2. 검색기능
-- 나라로 검색 : 
select * from foods where country like '%터%';
-- 업종으로 검색
select * from foods where kind like '%양%';
-- 가게명으로 검색
select * from foods where store_name like '%브라질%';
-- 주소로 검색
select * from foods where address like '%서울%';

--3. 삭제기능
delete from foods where restaurant_no=3;

--4. 변경기능
update foods set kind='양식', country='베네수엘라', store_name='리우', address='서울시', homepage='localhost', phone_number='02-1234-1234', file_name='asdf.jpg'  where food_no=1;

--5. 상세조회
select * from foods where food_no=1;

--6. 전체조회
select * from foods;