package report;

import java.util.List;

import classscheduler.Activity;
import classscheduler.StudentActivity;

public class ActivityReportDto {

	String activityName;
	String hour;
	String time;
	List<StudentActivity> students;
	Activity activity;
	

	public ActivityReportDto(String activityName, String hour, String time,
			List<StudentActivity> students, Activity activity) {
		super();
		this.activityName = activityName;
		this.hour = hour;
		this.time = time;
		this.students = students;
		this.activity = activity;
	}
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}

	public List<StudentActivity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentActivity> students) {
		this.students = students;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
