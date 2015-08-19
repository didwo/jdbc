package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		try{
		//1. 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.Connection 얻기
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		
		System.out.println("DB연결성공");
		
		}catch(ClassNotFoundException ex){
			System.out.println("JDBC 라이브러리를 찾을 수 없습니다.");
			
		}catch(SQLException ex){
			System.out.println("DBMS와 연결 할 수 없습니다.");
			
		} finally{
			//3.자원정리
			try{
				if(conn != null)
					conn.close();
			}catch(SQLException ex){
				
			}
			
		}
		
	}

}
