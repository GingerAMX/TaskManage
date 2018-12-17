package dto;

public class TaskContent {
	private String taskName;
	private int taskID;
	private String name;
	private int deadline;
	private String text;

	public TaskContent(){

	}

	public TaskContent(String taskName, int taskID, String name, int deadline, String text) {
		this.taskName = taskName;
		this.taskID = taskID;
		this.name = name;
		this.deadline = deadline;
		this.text = text;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
