/*
	## ���������̺� ���� DML
	-- ���̺�� : foods
	-- ��ũ��Ʈ���ϸ� : foods_dml.sql

	-- ���̺� ����

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

--1. ��ϱ��
insert into foods
values(1, '���', '�����', '�ػ罺 �� �����', '����Ư���� ���ʱ� ������ 118-3', 'http://www.texasdebrazil.com/','02-6282-5000', 'sa_01.jpg');

--2. �˻����
-- ����� �˻� : 
select * from foods where country like '%��%';
-- �������� �˻�
select * from foods where kind like '%��%';
-- ���Ը����� �˻�
select * from foods where store_name like '%�����%';
-- �ּҷ� �˻�
select * from foods where address like '%����%';

--3. �������
delete from foods where restaurant_no=3;

--4. ������
update foods set kind='���', country='���׼�����', store_name='����', address='�����', homepage='localhost', phone_number='02-1234-1234', file_name='asdf.jpg'  where food_no=1;

--5. ����ȸ
select * from foods where food_no=1;

--6. ��ü��ȸ
select * from foods;