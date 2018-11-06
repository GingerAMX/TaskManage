package dto;

public class Task {
	private int taskID;
	private String taskName;
	private String text;
	private int tID;
	private int cID;
	private int deadline;

	public Task(){

	}

	public Task(int taskID, String taskName, String text, int tID, int cID, int deadline) {
		this.taskID = taskID;
		this.taskName = taskName;
		this.text = text;
		this.tID = tID;
		this.cID = cID;
		this.deadline = deadline;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int gettID() {
		return tID;
	}

	public void settID(int tID) {
		this.tID = tID;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

}
