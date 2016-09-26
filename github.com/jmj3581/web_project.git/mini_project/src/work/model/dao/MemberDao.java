package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import work.model.dto.Member;
import work.util.Utility;

public class MemberDao {
	
	private FactoryDao factory = FactoryDao.getInstance();
	
	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		return instance;
	}
	//	--1. 등록기능
	//	insert into members
	//	values('test01', 'test01','정민지','010-1234-1111','test01@naver.com','서울시 노원구 덕릉로 77길5 벽산아파트 103동 601호',0,'2016/08/26','M');
	public int insertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String userId = member.getUserId();
		String userPw = member.getUserPw();
		String name = member.getName();
		String mobile = member.getMobile();
		String email = member.getEmail();
		String address = member.getAddress();
		int visitCount = member.getVisitCount();
		String entryDate = member.getEntryDate();
		String grade = member.getGrade();
		
		try {
			conn = factory.getConnection();
			String sql = "insert into members values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			pstmt.setString(3, name);
			pstmt.setString(4, mobile);
			pstmt.setString(5, email);
			pstmt.setString(6, address);
			pstmt.setInt(7, visitCount);
			pstmt.setString(8, entryDate);
			pstmt.setString(9, grade);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 등록 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	//	--2. 전체 회원 조회 기능
	//	select * from members;
	public ArrayList<Member> selectList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Member> memList = new ArrayList<Member>();
		Member member = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from members";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			String userId = null;
			String userPw = null;
			String name = null;
			String mobile = null;
			String email = null;
			String address = null;
			int visitCount = 0;
			String entryDate = null;
			String grade = null;
			
			while(rs.next()) {
				userId = rs.getString("user_id");
				userPw = rs.getString("user_pw");
				name = rs.getString("name");
				mobile = rs.getString("mobile");
				email = rs.getString("email");
				address = rs.getString("address");
				visitCount = rs.getInt("visit_count");
				entryDate = rs.getString("entry_date");
				grade = rs.getString("grade"); 
				
				member = new Member(userId, userPw, name, mobile, email, address, visitCount, entryDate, grade);
				memList.add(member);
			}
			//return memList;
		} catch (SQLException e) {
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return memList;
	}
	
	//	--3. 특정 회원 상세 조회 기능:
	//	-- 아이디 : select * from members where user_id = 'test05';
	public Member selectMember(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member dto = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from members where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userPw = rs.getString("user_pw");
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String address = rs.getString("address");
				int visitCount = rs.getInt("visit_count"); 
				String entryDate = rs.getString("entry_date");
				String grade = rs.getString("grade");
				
				dto = new Member(userId, userPw, name, mobile, email, address, visitCount, entryDate, grade);
			}
		} catch (SQLException e) {
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return dto;
	}
	
	//	--4. 특정회원정보 전체 변경
	//	--사용자: 등급, 가입일, 방문횟수 변경 불가항목
	//	--      비밀번호, 이름, 연락처, 이메일->변경가능항목
	//	update members set 
	//	user_pw='test01', name='정민지', mobile='010-1234-1111', email= 'test01@naver.com' 
	//	where user_id='test01';
	public int updateMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String userId = member.getUserId();
		String userPw = member.getUserPw();
		String name = member.getName();
		String mobile = member.getMobile();
		String email = member.getEmail();
		String address = member.getAddress();
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members set ");
			sql.append("user_pw=?, name=?, mobile=?, email=?, address=? ");
			sql.append("where user_id=?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userPw);
			pstmt.setString(2, name);
			pstmt.setString(3, mobile);
			pstmt.setString(4, email);
			pstmt.setString(5, address);
			pstmt.setString(6, userId);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 변경 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//	--관리자:모든정보 변경가능
	//	update members set 
	//	user_pw='bluesky', name='이하니', mobile='010-6666-7674', email='hony@naver.com', grade='G', entry_date='2016/07/29', visit_count=2
	//	where user_id='test03';
	public int updateAll(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String userId = member.getUserId();
		String userPw = member.getUserPw();
		String name = member.getName();
		String mobile = member.getMobile();
		String email = member.getEmail();
		String address = member.getAddress();
		int visitCount = member.getVisitCount();
		String entryDate = member.getEntryDate();
		String grade = member.getGrade();
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members set ");
			sql.append("user_pw=?, name=?, mobile=?, email=?, address=?, visit_count=?, entry_date=?, grade=? ");
			sql.append("where user_id=?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, userPw);
			pstmt.setString(2, name);
			pstmt.setString(3, mobile);
			pstmt.setString(4, email);
			pstmt.setString(5, address);
			pstmt.setInt(6, visitCount);
			pstmt.setString(7, entryDate);
			pstmt.setString(8, grade);
			pstmt.setString(9, userId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 변경 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//	--5. 특정회원 탈퇴 기능
	//	delete from members where user_id='test04';
	public int deleteMember(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = factory.getConnection();
			String sql = "delete from members where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
 	
	//	--6. 아이디 찾기
	//	select user_id from members where name='정민지' and email='test01@naver.com';
	public String selectId(int type, String name, String data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = null;
			if(type == 1) {
				sql = "select user_id from members where name=? and mobile=?";
			} else {
				sql = "select user_id from members where name=? and email=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, data);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String userId = rs.getString("user_id");
				return userId;
			}
		} catch (SQLException e) {
			System.out.println("Error : 아이디 찾기 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	//	--7. 비밀번호 찾기
	//	select user_pw from members where user_id='test02' and mobile='010-1111-1111';
	public String selectPw(int type, String userId, String data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = null;
			if(type == 3) {
				sql = "select name from members where user_id=? and mobile=?";
			} else {
				sql = "select name from members where user_id=? and email=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, data);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String pw = Utility.getSecureCodeNumberAndAlphabet(6);
				pstmt.close();
				sql = "update members set user_pw=? where user_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pw);
				pstmt.setString(2, userId);
				if(pstmt.executeUpdate() == 1) {
					return pw;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error : 아이디 찾기 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	//	--8. 로그인 : 로그정보추후고려
	//	select name, grade from members where user_id='test02' and user_pw='test02';
	public HashMap<String, String> selectLogin(String userId, String userPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			conn = factory.getConnection();
			String sql = "select name, grade from members where user_id=? and user_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String grade = rs.getString("grade");
				map.put("name", name);
				map.put("grade", grade);
				return map;
			}
		} catch (SQLException e) {
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	//	--9. 비밀번호 변경
	//	update members set
	//	user_pw='user01' 
	//	where user_id='test01';
	public int updatePw(String userId, String userPw, String changePw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = factory.getConnection();
			String sql = "update members set user_pw=?  where user_id=? and user_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, changePw);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPw);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 비밀번호 변경 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//	--10.아이디 중복 조회
	//	select name from members where user_id='test01';
	public int selectDuplicateId(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select name from members where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
//				return name;
				if(name.equals(null)) {
					return 0;
				} else {
					return 1;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return 0;
	}
	
	//	-- 11. 관리자 회원 검색
	//	---핸드폰 뒷 4자리 회원 조회 : select * from members where mobile like '%2222';
	//	--이름부분 매칭 조회 : select * from members where name like '%이%';
	//	--아이디 부분 매칭 조회 : select * from members where user_id like '%03%';
	//	--방문횟수 : select * from members where visit_count like '%1%';
	public ArrayList<Member> selectData(String keyName, String data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		ArrayList<Member> memList = new ArrayList<Member>();
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from members where ");
			sql.append(keyName);
			sql.append(" like ? ");
//			String sql = "select * from members where ? like ?";
			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, keyName);
			pstmt.setString(1, "%"+ data +"%");
			rs = pstmt.executeQuery();
			
			String userId = null;
			String userPw = null;
			String name = null;
			String mobile = null;
			String email = null;
			String address = null;
			int visitCount = 0;
			String entryDate = null;
			String grade = null;
			
			while (rs.next()) {
				userId = rs.getString("user_id");
				userPw = rs.getString("user_pw");
				name = rs.getString("name");
				mobile = rs.getString("mobile");
				email = rs.getString("email");
				address = rs.getString("address");
				visitCount = rs.getInt("visit_count");
				entryDate = rs.getString("entry_date");
				grade = rs.getString("grade");
				
				member = new Member(userId, userPw, name, mobile, email, address, visitCount, entryDate, grade);
				memList.add(member);
			}
			return memList;
		} catch (SQLException e) {
			System.out.println("Error : 부분검색조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
}
