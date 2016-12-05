package viser.document.service;

import java.util.Date;

public class ListDocumentForm {
	private int documentNo;
	private String departmentName;
	private String positionName;
	private String name;
	private String doctypeName;
	private String title;
	private String content;
	private String officerDepartment;
	private String officerPosition;
	private String officerName;
	private boolean officerCheck;
	private Date regDate;
	
	public ListDocumentForm(){ }
	
	public ListDocumentForm(int documentNo, String departmentname, String positionName, String name
			, String doctypeName, String title, String content, String officerDepartment, String officerPosition
			, String officerName, boolean officerCheck, Date regDate){
		
		this.documentNo = documentNo;
		this.departmentName = departmentname;
		this.positionName = positionName;
		this.name = name;
		this.doctypeName = doctypeName;
		this.title = title;
		this.content = content;
		this.officerDepartment = officerDepartment;
		this.officerPosition = officerPosition;
		this.officerName = officerName;
		this.officerCheck = officerCheck;
		this.regDate = regDate;
	}

	public int getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoctypeName() {
		return doctypeName;
	}

	public void setDoctypeName(String doctypeName) {
		this.doctypeName = doctypeName;
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

	public boolean isOfficerCheck() {
		return officerCheck;
	}

	public void setOfficerCheck(boolean officerCheck) {
		this.officerCheck = officerCheck;
	}
	
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
