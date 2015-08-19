package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HRSerchEmployee {
	Statement stmt = null;

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			Scanner sc = new Scanner(System.in);
			String keyword = sc.next();
			sc.close();
		
		
				
		
			System.out.println(keyword);
		
		

			//1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection 얻기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			
			System.out.println("DB연결성공");
			
			//3.Statement 생성
			stmt = conn.createStatement();
			
			//4.sql문 수행
			String sql="select first_name,"
					+ 		"last_name, "
					+ 		"email,"
					+ 		"phone_number, "
					+ 		"hire_date "
					+ 		"from employees "
					+ 		"where first_name = '"+keyword+"'"
					+ 		"or last_name='"+keyword+"'";			
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String first_name = rs.getString(1);
				String last_name = rs.getString(2);
				String email = rs.getString(3);
				String phonenumber = rs.getString(4);///////////////////////
				String hire_date = rs.getString(5);
				System.out.println(first_name+last_name+email+phonenumber+hire_date);
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



