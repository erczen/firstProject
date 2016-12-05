package viser.account.service;

public class User {

	private String id;
	private String name;
	private String departmentName;
	private String positionName;
	
	public User(){}
	
	public User(String id, String name, String departmentName, String positionName) {
		this.id = id;
		this.name = name;
		this.departmentName = departmentName;
		this.positionName = positionName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}
