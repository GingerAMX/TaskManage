package dto;

public class Manager {
	private int tID;
	private String tName;

	public void manager() {

	}

	public Manager(int tID, String tName) {
		this.tID = tID;
		this.tName = tName;
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

}
