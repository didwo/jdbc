package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HRSalary2 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
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
			
			
			//3.sql문 준비
			String sql="select first_name || ' ' || last_name name,"
					+ 	"salary from employees "
					+ 	"where salary >=?" +' '
					+ 	"and salary <=?"+' '
					+ 	"order by salary" ;
			
			
			//sql문을 pstmt에 넣는다
			pstmt = conn.prepareStatement(sql);
			
			//binding
			pstmt.setInt(1, minsalary);
			pstmt.setInt(2, maxsalary);
			
			ResultSet rs = pstmt.executeQuery();
			
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
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				}catch(SQLException ex){
					
				}
				
			}
		}

	



		

	}
