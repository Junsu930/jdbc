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

public class JDBCExample3 {
	public static void main(String[] args) {
		
		
		
		
		Scanner sc = new Scanner(System.in);
		
		// 부서명을 입력받아 같은 부서에 있는 사원의
		// 사원명, 부서명, 급여 조회
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1521";
			String sid = ":XE";
			String user = "kh";
			String pw = "kh1234";

			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
											// jdbc:oracle:thin:@localhost:1521:XE
			System.out.print("부서명 입력 : ");
			String input = sc.nextLine();
			
			String query = "SELECT EMP_NAME, NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE, SALARY FROM EMPLOYEE LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)"
					+ "WHERE NVL(DEPT_TITLE, '부서없음') = " + "'" + input + "'";
			
			// (중요!)
			// JAVA에서 작성되는 SQL에
			// 문자열 변수를 추가할 경우 
			// '' (DB문자열 리터럴)이 누락되지 않도록 신경쓸 것!
			
			// 만약 '' 미작성 시 String 값은 컬럼명으로 인식되어 
			// 부적합한 식별자 오류가 발생한다.
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			// 조회 결과(rs)를 List에 옮겨 담기
			List<Emp>list = new ArrayList<Emp>();
			
			while(rs.next()) { // 다음 행으로 이동해서 해당 행에 데이터가 있으면 true
				
				String empName = rs.getString("EMP_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				int salary = rs.getInt("SALARY");
				
				Emp emp = new Emp(empName, deptTitle,salary);
			
				list.add(emp);
			}
			
			// List에 추가된 Emp 객체가 만약 없다면 "조회 결과 없습니다"
			// 있다면 순차적 출력
			
			if(list.isEmpty()) { // 리스트가 비어있을 경우
				System.out.println("조회 결과 없습니다.");
			} else {

				// 향상된 for문 
				for(Emp emp : list) {
					System.out.println(emp);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


