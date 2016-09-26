/*
	## ���������̺� ��Ű�� ��ũ��Ʈ ����
	-- ���������̺�� : foods
	-- ��ũ��Ʈ���ϸ� : foods_ddl.sql

	## ȸ�� ��ƼƼ �м� : ���̺�� FOODS
	1. ��������ȣ : FOOD_NO NUMBER(5) PRIMARY KEY
	2. ���� : KIND VARCHAR2(50) NOT NULL
	3. ���� : COUNTRY VARCHAR2(30) NOT NULL
	4. �������� : STORE_NAME VARCHAR2(30) NOT NULL
	5. �ּ� : ADDRESS VARCHAR2(100) NOT NULL
	6. Ȩ�������ּ� : HOMEPAGE VARCHAR2(70)
	7. ��������ȭ��ȣ : PHONE_NUMBER VARCHAR2(15)
	8. ���ϸ� : FILE_NAME VARCHAR2(50)
	
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