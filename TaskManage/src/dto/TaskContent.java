package dto;

public class TaskContent {
	private String taskName;
	private String tName;
	private int deadline;
	private String text;

	public TaskContent(){

	}

	public TaskContent(String taskName, String tName, int deadline, String text) {
		this.taskName = taskName;
		this.tName = tName;
		this.deadline = deadline;
		this.text = text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
