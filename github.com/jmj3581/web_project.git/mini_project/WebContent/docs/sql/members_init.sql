/*
	## ȸ�����̺� ���� DML
	-- ȸ�����̺�� : members
	-- ��ũ��Ʈ���ϸ� : members_init.sql
	-- ȸ������ �ý��� ������ ���� �ʱ�ȭ ������

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

insert into members
values('test01', 'test01','������','010-1234-1111','test01@naver.com','����� ����� ������ 77��5 �������Ʈ 103�� 601ȣ',0,'2016/08/26','M');

insert into members
values('test02', 'test02','������','010-1111-1111','test02@gmail.com','����� ����� ������ 77��5 �������Ʈ 103�� 701ȣ',1,'2016/08/26','G');

insert into members
values('test03', 'test03','���ּ�','010-2222-2222','test03@nate.com','��⵵ �����ֽ� ������ 77��5 �������Ʈ 103�� 801ȣ',0,'2016/08/26','G');

insert into members
values('test04', 'test04','���ر�','010-455-4455','test04@syu.com','�뱸�� ������ ������ 77��5 �������Ʈ 103�� 201ȣ',0,'2016/08/26','M');

insert into members
values('test05', 'test05','�ڿ���','010-5555-5555','test05@empal.com','����� ����� ������ 77��5 �������Ʈ 103�� 101ȣ',0,'2016/08/26','G');

insert into members
values('test06', 'test06','���¸�','010-6666-6666','test06@naver.com','�����ֽ� ���� ������ 77��5 �������Ʈ 103�� 1001ȣ',0,'2016/08/26','G');
