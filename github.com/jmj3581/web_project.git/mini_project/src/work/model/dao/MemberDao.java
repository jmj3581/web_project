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
	//	--1. ��ϱ��
	//	insert into members
	//	values('test01', 'test01','������','010-1234-1111','test01@naver.com','����� ����� ������ 77��5 �������Ʈ 103�� 601ȣ',0,'2016/08/26','M');
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
			System.out.println("Error : ��� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	//	--2. ��ü ȸ�� ��ȸ ���
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return memList;
	}
	
	//	--3. Ư�� ȸ�� �� ��ȸ ���:
	//	-- ���̵� : select * from members where user_id = 'test05';
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return dto;
	}
	
	//	--4. Ư��ȸ������ ��ü ����
	//	--�����: ���, ������, �湮Ƚ�� ���� �Ұ��׸�
	//	--      ��й�ȣ, �̸�, ����ó, �̸���->���氡���׸�
	//	update members set 
	//	user_pw='test01', name='������', mobile='010-1234-1111', email= 'test01@naver.com' 
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
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//	--������:������� ���氡��
	//	update members set 
	//	user_pw='bluesky', name='���ϴ�', mobile='010-6666-7674', email='hony@naver.com', grade='G', entry_date='2016/07/29', visit_count=2
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
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//	--5. Ư��ȸ�� Ż�� ���
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
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
 	
	//	--6. ���̵� ã��
	//	select user_id from members where name='������' and email='test01@naver.com';
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
			System.out.println("Error : ���̵� ã�� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	//	--7. ��й�ȣ ã��
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
			System.out.println("Error : ���̵� ã�� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	//	--8. �α��� : �α��������İ��
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	//	--9. ��й�ȣ ����
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
			System.out.println("Error : ��й�ȣ ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//	--10.���̵� �ߺ� ��ȸ
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return 0;
	}
	
	//	-- 11. ������ ȸ�� �˻�
	//	---�ڵ��� �� 4�ڸ� ȸ�� ��ȸ : select * from members where mobile like '%2222';
	//	--�̸��κ� ��Ī ��ȸ : select * from members where name like '%��%';
	//	--���̵� �κ� ��Ī ��ȸ : select * from members where user_id like '%03%';
	//	--�湮Ƚ�� : select * from members where visit_count like '%1%';
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
			System.out.println("Error : �κа˻���ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
}
