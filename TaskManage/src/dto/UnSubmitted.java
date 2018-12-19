package dto;

public class UnSubmitted {
	private int sID;
	private String sName;

	public void unSubmitted(){

	}

	public UnSubmitted(int sID, String sName) {
		this.sID = sID;
		this.sName = sName;
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

}