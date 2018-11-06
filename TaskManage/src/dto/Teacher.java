package dto;

public class Teacher {
	private int tID;
	private String tName;
	private String pass;
	private String mPass;

	public Teacher(){

	}

	public Teacher(int tID, String tName, String pass, String mPass) {
		this.tID = tID;
		this.tName = tName;
		this.pass = pass;
		this.mPass = mPass;
	}

	public int gettID() {
		return tID;
	}

	public void settID(int tID) {
		this.tID = tID;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getmPass() {
		return mPass;
	}

	public void setmPass(String mPass) {
		this.mPass = mPass;
	}

}
