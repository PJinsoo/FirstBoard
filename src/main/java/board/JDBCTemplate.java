package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	public static Connection getConnection() {
		try {
			//드라이버 연결
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//드라이버 연결 실패
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/boarddb";
		String id = "root"; //MySQL ID
		String pw = "root"; //MYSQL PW
		
		Connection conn = null;
		
		try {
			//DB 접속
			conn = DriverManager.getConnection(url, id, pw);
			conn.setAutoCommit(false);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static boolean isConnection(Connection conn) {
		boolean valid = true;
		
		try {
			if(conn == null || conn.isClosed()) {
				valid = false;
			}
		} catch(SQLException e) {
			valid = true;
			e.printStackTrace();
		}
		return valid;
	}
	
	public static void close(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}