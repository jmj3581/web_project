package work.model.service;

import java.util.ArrayList;

import work.model.dao.BoardDao;
import work.model.dto.Board;
import work.util.Utility;

public class BoardService {
	/** 게시판 dao 클래스 */
	private BoardDao dao = BoardDao.getInstance();
	
	/** 게시글 등록기능 */
	public int registerBoard(Board dto) {
		dto.setRegDate(Utility.getCurrentDate("yyyy/MM/dd"));
		dto.setCount(0);
		return dao.insertBoard(dto);
	}
	
	/** 게시글 삭제 기능 */
	public int removeBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}
	
	/** 게시글 전체 조회 기능 */
	public ArrayList<Board> listBoard(int pageNum) {
		return dao.selectList(pageNum);
	}
	
	/** 게시글 상세 조회 */
	public Board detailBoard(int boardNo) {
		return dao.selectBoard(boardNo);
	}
	
	/** 게시글 변경 기능 */
	public int changeBoard(Board dto) {
		return dao.updateBoard(dto);
	}

	/** 게시글 검색 기능 */
	public ArrayList<Board> searchBoard(String key, String data) {
		return dao.selectData(key, data);
	}
	
	/** 게시글 조회수 증가 */
	public int changeCount(int boardNo) {
		return dao.updateCount(boardNo);
	}
	
	/** 전체 게시글 갯수 조회 */
	public int totalCount() {
		return dao.totalCount();
	}
}
