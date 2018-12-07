package dto;

public class Submitted {
	private int sID;
	private String sName;
	private int date;

	public Submitted() {

	}

	public Submitted(int sID, String sName, int date) {
		this.sID = sID;
		this.sName = sName;
		this.date = date;
	}

	public int getsID() {
		return sID;
	}

	public void setsID(int sID) {
		this.sID = sID;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

}