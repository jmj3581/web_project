package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Board;

public class BoardDao {
	private FactoryDao factory = FactoryDao.getInstance();
	
	private static BoardDao instance = new BoardDao();
	
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		return instance;
	}
	//	-- 1. 등록기능
	//	insert into boards
	//	values(seq_board_no.nextval, '제목입니다.', '아이디', '등록일', '내용입니다.', 5);
	public int insertBoard(Board dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String title = dto.getTitle();
		String userId = dto.getUserId();
		String regDate = dto.getRegDate();
		String contents = dto.getContents();
		int count = dto.getCount();
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into boards ");
			sql.append("values(seq_board_no.nextval, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, title);
			pstmt.setString(2, userId);
			pstmt.setString(3, regDate);
			pstmt.setString(4, contents);
			pstmt.setInt(5, count);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 등록 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	//	-- 2. 삭제기능
	//	delete from boards where board_no=1;
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			String sql = "delete from boards where seq_board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//	-- 3. 전체조회기능
	//	select * from boards;
	public ArrayList<Board> selectList(int pageNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> dto = new ArrayList<Board>();
		Board board = null;
		
		
		try {
			conn = factory.getConnection();
			String sql = "select count(*) from boards";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int total = 0;
			if (rs.next()) {
				total = rs.getInt(1);
				total = ((total / 10) == 0) ? (total / 10) : (total / 10) + 1;
			}
			
			if (pageNum == 0) {
				pageNum = 1;
			} else if (pageNum >= total) {
				pageNum = total;
			}

			pstmt.close();
			rs.close();

			StringBuilder paging = new StringBuilder();
			paging.append("select * ");
			paging.append("from( select rownum rown, A.* ");
			paging.append("      from( select *");
			paging.append("            from boards");
			paging.append("			   order by seq_board_no desc");
			paging.append("      )A");
			paging.append(")");
			paging.append("where rown>=? and rown<=?");

			pstmt = conn.prepareStatement(paging.toString());
			pstmt.setInt(1, ((pageNum - 1) * 10) + 1);
			pstmt.setInt(2, ((pageNum) * 10));
			rs = pstmt.executeQuery();

			int boardNo = 0;
			String title = null;
			String userId = null;
			String regDate = null;
			String contents = null;
			int count = 0;
			
			while(rs.next()) {
				boardNo = rs.getInt("seq_board_no");
				title = rs.getString("title");
				userId = rs.getString("user_id");
				regDate = rs.getString("reg_date");
				contents = rs.getString("contents");
				count = rs.getInt("count");
				
				board = new Board(boardNo, title, userId, regDate, contents, count);
				dto.add(board);
			}
		} catch (SQLException e) {
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	//	-- 4. 상세조회기능
	//	select * from boards where seq_board_no=1;
	public Board selectBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board dto = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from boards where seq_board_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String title = rs.getString("title");
				String userId = rs.getString("user_id");
				String regDate = rs.getString("reg_date");
				String contents = rs.getString("contents");
				int count = rs.getInt("count");
				
				dto = new Board(boardNo, title, userId, regDate, contents, count);
			}
		} catch (SQLException e) {
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return dto;
	}
	
	//	-- 5. 변경기능 
	//	update boards set
	//	title='제목', user_id='id', reg_date='2016/08/30', contents='contents' where board_no=1;
	public int updateBoard(Board dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int boardNo = dto.getBoardNo();
		String title = dto.getTitle();
		String contents = dto.getContents();
		
		try{
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update boards set ");
			sql.append("title=?, contents=? ");
			sql.append("where seq_board_no=?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, boardNo);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 변경 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	// --6. 검색
	public ArrayList<Board> selectData(String keyName, String data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board dto = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from boards where ");
			sql.append(keyName);
			sql.append(" like ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+ data +"%");
			rs = pstmt.executeQuery();
			
			int boardNo = 0;
			String title = null;
			String userId = null;
			String regDate = null;
			String contents = null;
			int count = 0;
			
			while (rs.next()) {
				boardNo = rs.getInt("seq_board_no");
				title = rs.getString("title");
				userId = rs.getString("user_id");
				regDate = rs.getString("reg_date");
				contents = rs.getString("contents");
				count = rs.getInt("count");
				
				dto = new Board(boardNo, title, userId, regDate, contents, count);
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Error : 부분검색조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//--7. 조회수 증가
	public int updateCount(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update boards set ");
			sql.append("count=count+1 ");
			sql.append("where seq_board_no=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 변경 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//--8. 전체 게시글 갯수구하기
	public int totalCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = factory.getConnection();
			String sql = "select count(seq_board_no) from boards";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int total = rs.getInt(1);
				return total;
			}
		} catch (SQLException e) {
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return 0;
	}
}	
