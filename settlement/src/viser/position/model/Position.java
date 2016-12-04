package viser.position.model;

public class Position {
	private int positionNo;
	private String positionName;
	
	public Position(){}
	
	public Position(int positionNo, String positionName){
		this.positionNo = positionNo;
		this.positionName = positionName;
	}
	
	public int getPositionNo() {
		return positionNo;
	}
	public void setPositionNo(int positionNo) {
		this.positionNo = positionNo;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}
