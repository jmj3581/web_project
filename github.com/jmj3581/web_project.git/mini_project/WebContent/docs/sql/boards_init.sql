/*
	## �Խ������̺� ���� DML
	-- ���̺�� : boards
	-- ��ũ��Ʈ���ϸ� : boards_init.sql

	-- ���̺� ����
 Name                    Null?    Type
 ----------------------- -------- ----------------
 SEQ_BOARD_NO            NOT NULL NUMBER(5)
 TITLE                   NOT NULL VARCHAR2(30)
 USER_ID                 NOT NULL VARCHAR2(20)
 REG_DATE                NOT NULL VARCHAR2(15)
 CONTENTS                NOT NULL VARCHAR2(500)
 COUNT                   NOT NULL NUMBER(5)
 
*/

insert into boards
values(seq_board_no.nextval, '�����Դϴ�.', '���̵�', '2016/08/31', '�����Դϴ�.', 5);

insert into boards
values(seq_board_no.nextval, '�����Դϴ�.', 'test01', '2016/08/30', '�����Դϴ�.', 5);

insert into boards
values(seq_board_no.nextval, '�����Դϴ�.', 'test02', '2016/08/06', '�����Դϴ�.', 5);