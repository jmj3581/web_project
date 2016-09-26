/*
	## �Խ������̺� ��Ű�� ��ũ��Ʈ ����
	-- �Խ������̺�� : boards
	-- ��ũ��Ʈ���ϸ� : boards_ddl.sql

	## �Խ��� ��ƼƼ �м� : ���̺�� BOARD
	1. �Խ��� ��ȣ : SEQ_BOARD_NO NUMBER(5) PRIMARY KEY
	2. ���� : TITLE VARCHAR2(30) NOT NULL
	3. ���̵� : USER_ID VARCHAR2(20) NOT NULL
	4. ����� : REG_DATE VARCHAR2(15) NOT NULL
	5. ���� : CONTENTS VARCHAR2(500) NOT NULL
	6. ��ȸ�� : COUNT NUMBER(5) NOT NULL
	
*/
create sequence seq_board_no
	start with 1
	increment by 1
	maxvalue 99999
	minvalue 1
	nocycle;
	
create table boards(
	seq_board_no number(5) primary key,
	title varchar2(30) not null,
	user_id varchar2(20) not null,
	reg_date varchar2(15) not null,
	contents varchar2(500) not null,
	count number(5) not null
);