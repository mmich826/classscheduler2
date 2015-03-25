package classscheduler;
import java.util.ArrayList;
import java.util.List;


public class Student {
	private String name = "";
	private String grade = "";
	private String teacher = "";
	private List<String> act = new ArrayList<String>();
	private List<Boolean> actSchedList = new ArrayList<Boolean>();
	
	public Student() {
		super();
		for(int i=0; i<4; i++) {
			actSchedList.add(false);
		}
	}

	@Override
	public String toString() {
		return "Kid [name=" + name + ", grade=" + grade + ", teacher="
				+ teacher + ", act=" + act + ", actSchedList=" + actSchedList
				+ "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public List<String> getAct() {
		return act;
	}

	public void setAct(List<String> act) {
		this.act = act;
	}

	public List<Boolean> getActSchedList() {
		return actSchedList;
	}

	public void setActSchedList(List<Boolean> actSchedList) {
		this.actSchedList = actSchedList;
	}

}
