package dto;

public class TaskManage {
	private int id;
	private int sID;
	private int cID;
	private int taskID;
	private int date;
	private String text;

	public TaskManage(){

	}

	public TaskManage(int id, int sID, int cID, int taskID, int date, String text) {
		this.id = id;
		this.sID = sID;
		this.cID = cID;
		this.taskID = taskID;
		this.date = date;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getsID() {
		return sID;
	}

	public void setsID(int sID) {
		this.sID = sID;
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

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
