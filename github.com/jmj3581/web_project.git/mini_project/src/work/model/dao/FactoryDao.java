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
 * -- Ư���� ���� ����� �����ϴ� ���� Ŭ����
 * 
 * ## FactoryDao Pattern
 * -- DAO Ŭ������ ������ : Connection ����, �ڿ� ����
 * -- SingleTon Pattern ���� ����
 * 
 * ## DAO ������
 * 0. jdbc driver �ε� : ������
 * 1. Connection ����
 * 2. �ڿ�����
 * 
 * ## dbserver���� property ���� �ܺ� �ڿ����� ���
 * -- java.io.*;
 * -- java.util.ResourceBundle : ��Ģ�ؼ�, �����
 * 
 * ## ResourceBundle ��� ��Ģ
 * 1. �ܺ��ڿ����� ��ġ : classpath(output dir) ���� �����
 * 2. ���� Ȯ���� : .properties
 * 3. ���ϳ��� property ��� : key=value
 * 4. ���ǻ��� : key�� value�� = ��ȣ ���̿� ����Ұ�
 */
public class FactoryDao {
	// Connection Pool : javax.sql.DataSource
	// Conded name : java:comp/env
	// Resource name="jdbc/Oracle" : context.xml�� Resource name
	private String dsName = "java:comp/env/jdbc/Oracle";
	private DataSource ds;
	
	private static FactoryDao instance = new FactoryDao();
	
	/** 0. jdbc driver �ε� : ������ */
	private FactoryDao() {
		try {
			ds = (DataSource)new InitialContext().lookup(dsName);
		} catch(NamingException e) {
			System.out.println("DataSource �̸� �˻� ����");
			e.printStackTrace();
		}
	}
	
	public static FactoryDao getInstance() {
		return instance;
	}	
	
	/** 1. ������ : Connection ������ȯ */
	public Connection getConnection() {
		try {
			// Connection Pool (DataSource)���� ���� ��ü �ϳ� �����ͼ� ��ȯ
			return ds.getConnection();
		} catch(SQLException e) {
			System.out.println("DataSource ���ᰴü �������� ����");
			e.printStackTrace();
		}
		return null;
	}
	
	/** 2. �ڿ����� */
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
			System.out.println("Error : �ڿ����� ����");
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn, Statement stmt) {
		close(conn, stmt, null);
	}
}
