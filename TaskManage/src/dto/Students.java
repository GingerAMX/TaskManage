package dto;

public class Students {
	private int sID;
	private String sName;
	private String pass;

	public Students(){

	}

	public Students(int sID, String sName, String pass) {
		this.sID = sID;
		this.sName = sName;
		this.pass = pass;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
