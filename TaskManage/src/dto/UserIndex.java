package dto;

public class UserIndex {
	private String user;
	private int userID;

	public void userIndex(){

	}

	public UserIndex(String user, int userID) {
		this.user = user;
		this.userID = userID;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
