package viser.employee.model;

import java.util.Date;

public class Employee {
	private int employeeNo;
	private int departmentNo;
	private int positionNo;
	private String name;
	private Date birthday;
	
	public Employee(){}
	
	public Employee(int employeeNo, int departmentNo, int positionNo, String name, Date birthday){
		this.employeeNo = employeeNo;
		this.departmentNo = departmentNo;
		this.positionNo = positionNo;
		this.name = name;
		this.birthday = birthday;
	}
	
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public int getDepartmentNo() {
		return departmentNo;
	}
	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
	}
	public int getPositionNo() {
		return positionNo;
	}
	public void setPositionNo(int positionNo) {
		this.positionNo = positionNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
