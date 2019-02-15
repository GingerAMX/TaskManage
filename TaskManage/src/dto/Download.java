package dto;

public class Download {
	private int taskID;
	private String sName;
	private String path;

	public Download(){

	}

	public Download(int taskID, String sName, String path) {
		this.taskID = taskID;
		this.sName = sName;
		this.path = path;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}