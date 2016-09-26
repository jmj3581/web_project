/*
	## 회원테이블에 대한 DML
	-- 회원테이블명 : members
	-- 스크립트파일명 : members_init.sql
	-- 회원관리 시스템 개발을 위한 초기화 데이터

	-- 회원테이블 구조

 Name                    Null?    Type
 ----------------------- -------- ----------------
 USER_ID                 NOT NULL VARCHAR2(20)
 USER_PW                 NOT NULL VARCHAR2(20)
 NAME                    NOT NULL VARCHAR2(20)
 MOBILE                  NOT NULL VARCHAR2(14)
 EMAIL                   NOT NULL VARCHAR2(20)
 ADDRESES                         VARCHAR2(100)
 VISIT_COUNT                      NUMBER(5)
 ENTRY_DATE              NOT NULL VARCHAR2(10)
 GRADE                   NOT NULL VARCHAR2(1)
*/

insert into members
values('test01', 'test01','정민지','010-1234-1111','test01@naver.com','서울시 노원구 덕릉로 77길5 벽산아파트 103동 601호',0,'2016/08/26','M');

insert into members
values('test02', 'test02','김전수','010-1111-1111','test02@gmail.com','서울시 노원구 덕릉로 77길5 벽산아파트 103동 701호',1,'2016/08/26','G');

insert into members
values('test03', 'test03','이주선','010-2222-2222','test03@nate.com','경기도 남양주시 덕릉로 77길5 벽산아파트 103동 801호',0,'2016/08/26','G');

insert into members
values('test04', 'test04','장해궁','010-455-4455','test04@syu.com','대구시 수성구 덕릉로 77길5 벽산아파트 103동 201호',0,'2016/08/26','M');

insert into members
values('test05', 'test05','박연아','010-5555-5555','test05@empal.com','서울시 노원구 덕릉로 77길5 벽산아파트 103동 101호',0,'2016/08/26','G');

insert into members
values('test06', 'test06','문태림','010-6666-6666','test06@naver.com','남양주시 퇴계원 덕릉로 77길5 벽산아파트 103동 1001호',0,'2016/08/26','G');
