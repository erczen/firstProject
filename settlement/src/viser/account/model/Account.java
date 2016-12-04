package viser.account.model;

import java.util.Date;

public class Account {
	private String employeeId;
	private int employeeNo;
	private String password;
	private Date regdate;
	
	public Account(){}
	
	public Account(String employeeId, int employeeNo, String password, Date regdate){
		this.employeeId = employeeId;
		this.employeeNo = employeeNo;
		this.password = password;
		this.regdate = regdate;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}

	public void changePassword(String newPwd) {
		this.password = newPwd;
	}
}
