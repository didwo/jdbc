package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try{
		//1. 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.Connection 얻기
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		
		System.out.println("DB연결성공");
		
		//3.Statement 생성
		stmt = conn.createStatement();
		
		//4.sql문 수행
		String sql = "select * from employees";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			Long employeeId = rs.getLong(1);
			String firstName = rs.getString(2);
			System.out.println(employeeId+":"+firstName);
		}
		
		rs.close();
		}catch(ClassNotFoundException ex){
			System.out.println("JDBC 라이브러리를 찾을 수 없습니다.");
			
		}catch(SQLException ex){
			System.out.println("DB오류:" + ex);
			
		} finally{
			//3.자원정리
			try{
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(SQLException ex){
				
			}
			
		}
	}

}




