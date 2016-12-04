package viser.doctype.model;

public class Doctype {
	private int doctypeNo;
	private String doctypeName;
	
	public Doctype(){}
	
	public Doctype(int doctypeNo, String doctypeName){
		this.doctypeNo = doctypeNo;
		this.doctypeName = doctypeName;
	}
	
	public int getDoctypeNo() {
		return doctypeNo;
	}
	public void setDoctypeNo(int doctypeNo) {
		this.doctypeNo = doctypeNo;
	}
	public String getDoctypeName() {
		return doctypeName;
	}
	public void setDoctypeName(String doctypeName) {
		this.doctypeName = doctypeName;
	}
}
