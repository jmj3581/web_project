/*
	## ȸ�����̺� ��Ű�� ��ũ��Ʈ ����
	-- ȸ�����̺�� : members
	-- ��ũ��Ʈ���ϸ� : members_ddl.sql

	## ȸ�� ��ƼƼ �м� : ���̺�� MEMBERS
	1. ���̵� : USER_ID VARCHAR2(20) PRIMARY KEY
	2. ��й�ȣ : USER_PW VARCHAR2(20) NOT NULL
	3. �̸� : NAME VARCHAR2(20) NOT NULL
	4. ����ó : MOBILE VARCHAR2(14) NOT NULL
	5. �̸��� : EMAIL VARCHAR2(20) NOT NULL
	6. �ּ� : ADDRESS VARCHAR2(100)
	7. �湮Ƚ�� : VISIT_COUNT NUMBER(5)
	8. ������ : ENTRY_DATE VARCHAR2(10)
	9. ��� : GRADE VARCHAR2(1) NOT NULL
	
	## �Ӽ� ������
	-- ����ó : 010-1234-1234, 011-123-1234
	-- ������ : �⵵4�ڸ�/��2�ڸ�/��2�ڸ�
*/

create table members(
	user_id varchar2(20) primary key,
	user_pw varchar2(20) not null,
	name varchar2(20) not null,
	mobile varchar2(14) not null,
	email varchar2(20) not null,
	address varchar2(100),
	visit_count number(5),
	entry_date varchar2(10) not null,
	grade varchar2(1) not null
);