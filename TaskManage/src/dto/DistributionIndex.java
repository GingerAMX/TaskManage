package dto;

public class DistributionIndex {
	private String taskName;
	private int deadline;
	private int grade;
	private int cName;

	public DistributionIndex(){

	}

	public DistributionIndex(String taskName, int deadline, int grade, int cName) {
		this.taskName = taskName;
		this.deadline = deadline;
		this.grade = grade;
		this.cName = cName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getcName() {
		return cName;
	}

	public void setcName(int cName) {
		this.cName = cName;
	}

}
