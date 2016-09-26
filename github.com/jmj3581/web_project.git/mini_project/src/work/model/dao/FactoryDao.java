package work.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * ## Factory Pattern
 * -- 특정한 공통 기능을 제공하는 공장 클래스
 * 
 * ## FactoryDao Pattern
 * -- DAO 클래스의 공통기능 : Connection 생성, 자원 해제
 * -- SingleTon Pattern 적용 설계
 * 
 * ## DAO 공통기능
 * 0. jdbc driver 로딩 : 생성자
 * 1. Connection 생성
 * 2. 자원해제
 * 
 * ## dbserver관련 property 파일 외부 자원파일 사용
 * -- java.io.*;
 * -- java.util.ResourceBundle : 규칙준수, 사용편리
 * 
 * ## ResourceBundle 사용 규칙
 * 1. 외부자원파일 위치 : classpath(output dir) 기준 상대경로
 * 2. 파일 확장자 : .properties
 * 3. 파일내용 property 기술 : key=value
 * 4. 주의사항 : key와 value의 = 기호 사이에 공백불가
 */
public class FactoryDao {
	// Connection Pool : javax.sql.DataSource
	// Conded name : java:comp/env
	// Resource name="jdbc/Oracle" : context.xml의 Resource name
	private String dsName = "java:comp/env/jdbc/Oracle";
	private DataSource ds;
	
	private static FactoryDao instance = new FactoryDao();
	
	/** 0. jdbc driver 로딩 : 생성자 */
	private FactoryDao() {
		try {
			ds = (DataSource)new InitialContext().lookup(dsName);
		} catch(NamingException e) {
			System.out.println("DataSource 이름 검색 오류");
			e.printStackTrace();
		}
	}
	
	public static FactoryDao getInstance() {
		return instance;
	}	
	
	/** 1. 공통기능 : Connection 생성반환 */
	public Connection getConnection() {
		try {
			// Connection Pool (DataSource)에게 연결 객체 하나 가져와서 반환
			return ds.getConnection();
		} catch(SQLException e) {
			System.out.println("DataSource 연결객체 가져오기 오류");
			e.printStackTrace();
		}
		return null;
	}
	
	/** 2. 자원해제 */
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) {	
				rs.close();
			}
			
			if(stmt != null) {	
				stmt.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println("Error : 자원해제 오류");
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
}
