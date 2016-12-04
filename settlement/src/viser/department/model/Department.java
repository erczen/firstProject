package viser.department.model;

public class Department {
	private int departmentNo;
	private String departmentName;
	
	public Department(){}
	
	public Department(int departmentNo, String departmentName){
		this.departmentNo = departmentNo;
		this.departmentName = departmentName;
	}
	
	public int getDepartmentNo() {
		return departmentNo;
	}
	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
