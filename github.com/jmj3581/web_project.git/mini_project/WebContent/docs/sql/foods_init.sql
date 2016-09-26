/*
	## 음식점테이블에 대한 DML
	-- 테이블명 : foods
	-- 스크립트파일명 : foods_init.sql
	-- 음식점관리 시스템 개발을 위한 초기화 데이터

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
insert into foods
values(1, '양식', '브라질', '텍사스 데 브라질', '서울특별시 서초구 반포동 118-3', 'http://www.texasdebrazil.com/','02-6282-5000');

insert into foods
values(2, '남미'. '브라질', '따봉 브라질', '서울 용산구 이태원동 168-12 2층', '02-797-3363');

insert into foods
values(3, '남미', '브라질', '리우브라질', '서울시 마포구 서교동 예원빌딩 3층 364-3', 'https://www.facebook.com/riobrasilgarden/' , '02-325-4441');
