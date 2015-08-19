package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HRSalary {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			Scanner sc = new Scanner(System.in);
			int minsalary = sc.nextInt();
			int maxsalary = sc.nextInt();
			sc.close();
		
		
				
		
			System.out.println(minsalary+":"+maxsalary);
			
		
		

			//1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection 얻기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			
			System.out.println("DB연결성공");
			
			//3.Statement 생성
			stmt = conn.createStatement();
			
			//4.sql문 수행
			String sql="select first_name || ' ' || last_name name,"
					+ 	"salary from employees "
					+ 	"where salary >="+minsalary 
					+ 	"and salary <="+maxsalary
					+ 	"order by salary" ;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String name = rs.getString(1);
				Integer salary = rs.getInt(2);
				System.out.println(name+salary);
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
