package viser.document.model;

public class Document {
	private int documentNo;
	private int employeeNo;
	private int doctypeNo;
	private String title;
	private String content;
	private int officerNo;
	private boolean officerCheck;
	
	public Document(){}
	
	public Document(int documentNo, int employeeNo, int doctypeNo, String title, String content, int officerNo, boolean officerCheck){
		this.documentNo = documentNo;
		this.employeeNo = employeeNo;
		this.doctypeNo = doctypeNo;
		this.title = title;
		this.content = content;
		this.officerNo = officerNo;
		this.officerCheck = officerCheck;
	}
	
	public int getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public int getDoctypeNo() {
		return doctypeNo;
	}
	public void setDoctypeNo(int doctypeNo) {
		this.doctypeNo = doctypeNo;
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
	public int getOfficerNo() {
		return officerNo;
	}
	public void setOfficerNo(int officerNo) {
		this.officerNo = officerNo;
	}
	public boolean isOfficerCheck() {
		return officerCheck;
	}
	public void setOfficerCheck(boolean officerCheck) {
		this.officerCheck = officerCheck;
	}
}
