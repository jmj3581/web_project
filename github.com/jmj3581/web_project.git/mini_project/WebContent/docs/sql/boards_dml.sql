/*
	## �Խ������̺� ���� DML
	-- ���̺�� : boards
	-- ��ũ��Ʈ���ϸ� : boards_dml.sql

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
-- 1. ��ϱ��
insert into boards
values(seq_board_no.nextval, '�����Դϴ�.', '���̵�', '�����', '�����Դϴ�.', 5);

-- 2. �������
delete from boards where board_no=1;

-- 3. ��ü��ȸ���
select * from boards;

-- 4. ����ȸ���
select * from boards where board_no=1;

-- 5. ������ 
update boards set
title='����', user_id='id', reg_date='2016/08/30', contents='contents' where board_no=1;