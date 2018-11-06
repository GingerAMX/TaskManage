package dto;

public class Classs {
	private int cID;
	private String grade;
	private String cName;

	public Classs(){

	}

	public Classs(int cID, String grade, String cName) {
		this.cID = cID;
		this.grade = grade;
		this.cName = cName;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

}
