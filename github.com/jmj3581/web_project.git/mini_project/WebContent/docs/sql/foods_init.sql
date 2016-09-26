/*
	## ���������̺� ���� DML
	-- ���̺�� : foods
	-- ��ũ��Ʈ���ϸ� : foods_init.sql
	-- ���������� �ý��� ������ ���� �ʱ�ȭ ������

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
insert into foods
values(1, '���', '�����', '�ػ罺 �� �����', '����Ư���� ���ʱ� ������ 118-3', 'http://www.texasdebrazil.com/','02-6282-5000');

insert into foods
values(2, '����'. '�����', '���� �����', '���� ��걸 ���¿��� 168-12 2��', '02-797-3363');

insert into foods
values(3, '����', '�����', '��������', '����� ������ ������ �������� 3�� 364-3', 'https://www.facebook.com/riobrasilgarden/' , '02-325-4441');
