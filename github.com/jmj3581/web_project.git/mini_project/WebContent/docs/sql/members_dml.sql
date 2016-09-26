/*
	## ȸ�����̺� ���� DML
	-- ȸ�����̺�� : members
	-- ��ũ��Ʈ���ϸ� : members_dml.sql
	-- ȸ������ �ý��� ���� ��ɿ��� ����ϱ� ���� SQL

	-- ȸ�����̺� ����

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

--1. ��ϱ��
insert into members
values('test01', 'test01','������','010-1234-1111','test01@naver.com','����� ����� ������ 77��5 �������Ʈ 103�� 601ȣ',0,'2016/08/26','M');

--2. ��ü ȸ�� ��ȸ ���
select * from members;

--3. Ư�� ȸ�� �� ��ȸ ���:
-- ���̵� : select * from members where user_id = 'test05select * from ';

--4. Ư��ȸ������ ��ü ����
--�����: ���, ������, �湮Ƚ�� ���� �Ұ��׸�
--      ��й�ȣ, �̸�, ����ó, �̸���->���氡���׸�
update members set 
user_pw='test01', name='������', mobile='010-1234-1111', email= 'test01@naver.com' 
where user_id='test01';

--������:������� ���氡��
update members set 
user_pw='bluesky', name='���ϴ�', mobile='010-6666-7674', email='hony@naver.com', grade='G', entry_date='2016/07/29', visit_count=2
where user_id='test03';

--5. Ư��ȸ�� Ż�� ���
delete from members where user_id='test04';

--6. ���̵� ã��
select user_id from members where name='������' and email='test01@naver.com';

--7. ��й�ȣ ã��
select user_pw from members where user_id='test02' and mobile='010-1111-1111';

--8. �α��� : �α��������İ��
select name, grade from members where user_id='test02' and user_pw='test02';

--9. ��й�ȣ ����
update members set
user_pw='user01' 
where user_id='test01';

--10.���̵� �ߺ� ��ȸ
select name from members where user_id='test01';

-- 11. ������ ȸ�� �˻�
---�ڵ��� �� 4�ڸ� ȸ�� ��ȸ
select * from members where mobile like '%2222';
--�̸��κ� ��Ī ��ȸ
select * from members where name like '%��%';
--���̵� �κ� ��Ī ��ȸ
select * from members where user_id like '%03%';
--�湮Ƚ��
select * from members where visit_count like '1%';
