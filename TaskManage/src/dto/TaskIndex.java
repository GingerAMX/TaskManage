package dto;

public class TaskIndex {
	private String taskName;
	private String tName;
	private int deadline;

	public TaskIndex(){

	}

	public TaskIndex(String taskName, String tName, int deadline) {
		this.taskName = taskName;
		this.tName = tName;
		this.deadline = deadline;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

}
