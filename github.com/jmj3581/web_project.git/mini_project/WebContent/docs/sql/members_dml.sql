/*
	## 회원테이블에 대한 DML
	-- 회원테이블명 : members
	-- 스크립트파일명 : members_dml.sql
	-- 회원관리 시스템 관리 기능에서 사용하기 위한 SQL

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

--1. 등록기능
insert into members
values('test01', 'test01','정민지','010-1234-1111','test01@naver.com','서울시 노원구 덕릉로 77길5 벽산아파트 103동 601호',0,'2016/08/26','M');

--2. 전체 회원 조회 기능
select * from members;

--3. 특정 회원 상세 조회 기능:
-- 아이디 : select * from members where user_id = 'test05select * from ';

--4. 특정회원정보 전체 변경
--사용자: 등급, 가입일, 방문횟수 변경 불가항목
--      비밀번호, 이름, 연락처, 이메일->변경가능항목
update members set 
user_pw='test01', name='정민지', mobile='010-1234-1111', email= 'test01@naver.com' 
where user_id='test01';

--관리자:모든정보 변경가능
update members set 
user_pw='bluesky', name='이하니', mobile='010-6666-7674', email='hony@naver.com', grade='G', entry_date='2016/07/29', visit_count=2
where user_id='test03';

--5. 특정회원 탈퇴 기능
delete from members where user_id='test04';

--6. 아이디 찾기
select user_id from members where name='정민지' and email='test01@naver.com';

--7. 비밀번호 찾기
select user_pw from members where user_id='test02' and mobile='010-1111-1111';

--8. 로그인 : 로그정보추후고려
select name, grade from members where user_id='test02' and user_pw='test02';

--9. 비밀번호 변경
update members set
user_pw='user01' 
where user_id='test01';

--10.아이디 중복 조회
select name from members where user_id='test01';

-- 11. 관리자 회원 검색
---핸드폰 뒷 4자리 회원 조회
select * from members where mobile like '%2222';
--이름부분 매칭 조회
select * from members where name like '%이%';
--아이디 부분 매칭 조회
select * from members where user_id like '%03%';
--방문횟수
select * from members where visit_count like '1%';
