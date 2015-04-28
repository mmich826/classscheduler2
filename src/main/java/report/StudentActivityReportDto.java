package report;

public class StudentActivityReportDto {
	private String name = "";
	private String grade = "";
	private String teacher = "";

	private String activityName = "";
	private String activityLeader = "";
	private String hour = "";
	private String location = "";
	private String altLocation = "";
	
	
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

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}


	@Override
	public String toString() {
		return "StudentActivityReportDto [name=" + name + ", grade=" + grade
				+ ", teacher=" + teacher + ", activityName=" + activityName + ", hour=" + hour + "]";
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAltLocation() {
		return altLocation;
	}

	public void setAltLocation(String altLocation) {
		this.altLocation = altLocation;
	}

	public String getActivityLeader() {
		return activityLeader;
	}

	public void setActivityLeader(String activityLeader) {
		this.activityLeader = activityLeader;
	}

}
