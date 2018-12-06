package dto;

public class Download {
	private int cID;
	private int taskID;
	private String path;

	public Download(){

	}

	public Download(int cID, int taskID, String path) {
		this.cID = cID;
		this.taskID = taskID;
		this.path = path;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
