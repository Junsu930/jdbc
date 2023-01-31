package edu.kh.jdbc1.model.vo;

public class Employee {
	
	private String empName;
	private String jobName;
	private int salary;
	private int annualIncome; // 연봉
	private String gender;
	private String hireDate;
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public Employee() {}
	
	public Employee(String empName, String jobName, int salary, int annualIncome) {
		this.empName = empName;
		this.jobName = jobName;
		this.salary = salary;
		this.annualIncome = annualIncome;
	}
	
	

	public Employee(String empName, String gender, String hireDate) {
		this.empName = empName;
		this.gender = gender;
		this.hireDate = hireDate;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}

	@Override
	public String toString() {
		return   empName + " / " + jobName + " / " + salary + " / " + annualIncome;
	}
	
	
	
}
