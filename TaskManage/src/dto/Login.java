package dto;

public class Login {
	private int id;
	private String name;
	private int cID;

	public Login(){

	}

	public Login(int id, String name, int cID) {
		this.id = id;
		this.name = name;
		this.cID = cID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}


}
