package viser.employee.service;

import java.util.Map;

public class SearchEmployeeRequest {
	private int departmentNo;
	private int positionNo;
	private String name;
	
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
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, String.valueOf(departmentNo), "departmentName");
		checkEmpty(errors, String.valueOf(positionNo), "positionName");
		checkEmpty(errors, name, "name");
	}
	private void checkEmpty(Map<String, Boolean> errors, 
			String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}
