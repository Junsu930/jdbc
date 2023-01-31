package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample5 {
	public static void main(String[] args) {
		// 입사일을 입력("2002-09-06") 
		// 입력받은 값보다 먼저 입사한 사람의 
		// 이름 입사일 성별(M, F)조회
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String user = "kh";
			String pw = "kh1234";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			
			conn = DriverManager.getConnection(url, user, pw);
			
			System.out.print("입사일을 입력하세요 : ");
			String input = sc.next();
			
			String sql = "SELECT EMP_NAME, TO_CHAR(HIRE_DATE, 'YYYY\"년\" MM\"월\" DD\"일\"') HIRE_DATE, DECODE(SUBSTR(EMP_NO,8,1), 1, 'M', 2, 'F') GENDER FROM EMPLOYEE "
					+ "WHERE HIRE_DATE < TO_DATE('" +input + "')";
					
			// 문자열 내부에 쌍따옴표 작성 시 \"로 작성해야 한다. (ESCAPE문자)
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			List<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String hireDate = rs.getString("HIRE_DATE");
				String gender = rs.getString("GENDER");
				
				list.add(new Employee(empName, gender, hireDate));
				
			}
			
			
			if(list.size() == 0) {
				System.out.println("찾는 사원이 없습니다. ");
			}else {
				for(int i = 0; i<list.size(); i++) {
					System.out.printf("%02d) %s / %s/ %s\n", i+1, list.get(i).getEmpName(), list.get(i).getHireDate(), list.get(i).getGender());
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
