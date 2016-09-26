package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Food;

public class FoodDao {
	private FactoryDao factory = FactoryDao.getInstance();
	
	private static FoodDao instance = new FoodDao();
	
	private FoodDao() {}
	
	public static FoodDao getInstance() {
		return instance;
	}
	
	//	--1. ��ϱ��
	//	insert into foods
	//	values(1, '���', '�����', '�ػ罺 �� �����', '����Ư���� ���ʱ� ������ 118-3', 'http://www.texasdebrazil.com/','02-6282-5000');	
	public int insertFood(Food food) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int foodNo = food.getfoodNo();
		String kind = food.getkind();
		String country = food.getCountry();
		String storeName = food.getStoreName();
		String address = food.getAddress();
		String homepage = food.getHomepage();
		String phoneNumber = food.getPhoneNumber();
		String fileName = food.getFileName();
		System.out.println(">>> dao fileName : " + fileName);
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into foods ");
			sql.append("values(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, foodNo);
			pstmt.setString(2, kind);
			pstmt.setString(3, country);
			pstmt.setString(4, storeName);
			pstmt.setString(5, address);
			pstmt.setString(6, homepage);
			pstmt.setString(7, phoneNumber);
			pstmt.setString(8, fileName);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : ��� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	//	--2. �˻����
	//	-- ����� �˻� : 
	//	select * from foods where country like '%��%';
	//	-- �������� �˻�
	//	select * from foods where kind like '%��%';
	//	-- ���Ը����� �˻�
	//	select * from foods where store_name like '%�����%';
	//	-- �ּҷ� �˻�
	//	select * from foods where address like '%����%';
	public ArrayList<Food> selectData(String keyName, String data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Food food = null;
		ArrayList<Food> list = new ArrayList<Food>();
		
		try{
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from foods where ");
			sql.append(keyName);
			sql.append(" like ? ");
//			String sql = "select * from foods where ? like ?";
			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, keyName);
			pstmt.setString(1, "%" + data + "%");
			rs = pstmt.executeQuery();
			
			int foodNo = 0;
			String kind = null;
			String country = null;
			String storeName = null;
			String address = null;
			String homepage = null;
			String phoneNumber = null;
			String fileName = null;
			
			while(rs.next()) {
				foodNo = rs.getInt("food_no");
				kind = rs.getString("kind");
				country = rs.getString("country");
				storeName = rs.getString("store_name");
				address = rs.getString("address");
				homepage =rs.getString("homepage");
				phoneNumber = rs.getString("phone_number");
				fileName = rs.getString("file_name");
				
				food = new Food(foodNo, kind, country, storeName, address, homepage, phoneNumber, fileName);
				list.add(food);
			}
//			return list;
		} catch (SQLException e) {
			System.out.println("Error : �κа˻���ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
//		return null;
		return list;
	}
	
	//	--3. �������
	//	delete from foods where restaurant_no=3;
	public int deleteFood(int foodNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			String sql = "delete from foods where food_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, foodNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//	--4. ������
	//	update foods set dept='���', country='���׼�����', store_name='����', address='�����', homepage='localhost', phone_number='02-1234-1234'  where food_no=1;
	public int updateFood(Food food) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int foodNo = food.getfoodNo();
		String kind = food.getkind();
		String country = food.getCountry();
		String storeName = food.getStoreName();
		String address = food.getAddress();
		String homepage = food.getHomepage();
		String phoneNumber = food.getPhoneNumber();
		String fileName = food.getFileName();
		
		try{
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update foods set ");
			sql.append("kind=?, country=?, store_name=?, address=?, homepage=?, phone_number=?, file_name=? ");
			sql.append("where food_no=?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, kind);
			pstmt.setString(2, country);
			pstmt.setString(3, storeName);
			pstmt.setString(4, address);
			pstmt.setString(5, homepage);
			pstmt.setString(6, phoneNumber);
			pstmt.setString(7, fileName);
			pstmt.setInt(8, foodNo);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	//-- 5. ������ ����ȸ
	// select * from foods where food_no=1;
	public Food selectFood(int foodNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Food dto = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from foods where food_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, foodNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String kind = rs.getString("kind");
				String country = rs.getString("country");
				String storeName = rs.getString("store_name");
				String address = rs.getString("address");
				String homepage = rs.getString("homepage");
				String phoneNumber = rs.getString("phone_number");
				String fileName = rs.getString("file_name");
				System.out.println("dto filename"+fileName);
				dto = new Food(foodNo, kind, country, storeName, address, homepage, phoneNumber, fileName);
			}
		} catch (SQLException e) {
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return dto;
	}
	
	//	--6. ��ü ������ ��ȸ ���
	//	select * from foods;
	public ArrayList<Food> selectList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Food> dto = new ArrayList<Food>();
		Food food = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from foods order by food_no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int foodNo = 0;
			String kind = null;
			String country = null;
			String storeName = null;
			String address = null;
			String homepage = null;
			String phoneNumber = null;
			String fileName = null;
			
			while(rs.next()) {
				foodNo = rs.getInt("food_no");
				kind = rs.getString("kind");
				country = rs.getString("country");
				storeName = rs.getString("store_name");
				address = rs.getString("address");
				homepage = rs.getString("homepage");
				phoneNumber = rs.getString("phone_number");
				fileName = rs.getString("file_name");
				
				food = new Food(foodNo, kind, country, storeName, address, homepage, phoneNumber, fileName);
				dto.add(food);
			}
		} catch (SQLException e) {
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return dto;
	}
	
}
	