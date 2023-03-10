package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.kh.jdbc1.model.vo.Emp;
import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {
	public static void main(String[] args) {
			
		
		
		// 직급명, 급여를 입력받아
		// 해당 직급에서 입력받은 급여보다 많이 받는 사원의
		// 이름, 직급명, 급여, 연봉을 조회하여 출력
		// 단 조회 결과가 없으면 "조회 결과 없음" 출력
		
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
			
			System.out.print("검색할 직급명 : ");
			String inputJobName = sc.next();
			System.out.print("급여 입력 : ");
			int inputSalary = sc.nextInt();
			
			String query = "SELECT EMP_NAME, JOB_NAME, SALARY, 12*SALARY ANNUALINCOME FROM EMPLOYEE NATURAL JOIN JOB WHERE JOB_NAME =" + "'" + inputJobName +"'"
					+ " AND SALARY > " + inputSalary;
					
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			List<Employee>list = new ArrayList<Employee>();
			
			while(rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				int annualIncome = rs.getInt("ANNUALINCOME");
				
				list.add( new Employee(empName, jobName, salary, annualIncome));
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			} else {
				for(Employee employee : list) {
					System.out.println(employee);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
}
