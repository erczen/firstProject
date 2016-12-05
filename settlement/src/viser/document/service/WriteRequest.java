package viser.document.service;

import java.util.Map;

import viser.account.service.User;


public class WriteRequest {
	private User user;
	private String doctypeName;
	private String officerDepartment;
	private String officerPosition;
	private String officerName;
	private String title;
	private String content;
	
	public WriteRequest() {}
	
	public WriteRequest(User user, String doctypeName, String officerDepartment, String officerPosition, String officerName, String title, String content){
		this.user = user;
		this.officerDepartment = officerDepartment;
		this.officerPosition = officerPosition;
		this.doctypeName = doctypeName;
		this.officerName = officerName;
		this.title = title;
		this.content = content;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDoctypeName() {
		return doctypeName;
	}
	public void setDoctypeName(String doctypeName) {
		this.doctypeName = doctypeName;
	}
	public String getOfficerDepartment() {
		return officerDepartment;
	}

	public void setOfficerDepartment(String officerDepartment) {
		this.officerDepartment = officerDepartment;
	}

	public String getOfficerPosition() {
		return officerPosition;
	}

	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}

	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, doctypeName, "doctypeName");
		checkEmpty(errors, officerDepartment, "officerDepartment");
		checkEmpty(errors, officerPosition, "officerPosition");
		checkEmpty(errors, officerName, "officerName");
		checkEmpty(errors, title, "title");
		checkEmpty(errors, content, "content");
	}

	private void checkEmpty(Map<String, Boolean> errors, 
			String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}
