package work.model.service;

import java.util.ArrayList;

import work.model.dao.BoardDao;
import work.model.dto.Board;
import work.util.Utility;

public class BoardService {
	/** �Խ��� dao Ŭ���� */
	private BoardDao dao = BoardDao.getInstance();
	
	/** �Խñ� ��ϱ�� */
	public int registerBoard(Board dto) {
		dto.setRegDate(Utility.getCurrentDate("yyyy/MM/dd"));
		dto.setCount(0);
		return dao.insertBoard(dto);
	}
	
	/** �Խñ� ���� ��� */
	public int removeBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}
	
	/** �Խñ� ��ü ��ȸ ��� */
	public ArrayList<Board> listBoard(int pageNum) {
		return dao.selectList(pageNum);
	}
	
	/** �Խñ� �� ��ȸ */
	public Board detailBoard(int boardNo) {
		return dao.selectBoard(boardNo);
	}
	
	/** �Խñ� ���� ��� */
	public int changeBoard(Board dto) {
		return dao.updateBoard(dto);
	}

	/** �Խñ� �˻� ��� */
	public ArrayList<Board> searchBoard(String key, String data) {
		return dao.selectData(key, data);
	}
	
	/** �Խñ� ��ȸ�� ���� */
	public int changeCount(int boardNo) {
		return dao.updateCount(boardNo);
	}
	
	/** ��ü �Խñ� ���� ��ȸ */
	public int totalCount() {
		return dao.totalCount();
	}
}
