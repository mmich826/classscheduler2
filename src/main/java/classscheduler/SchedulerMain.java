package classscheduler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import report.ReportManager;
import scheduler.ActivityScheduler;
import scheduler.GradeActivityScheduler;
import file.ActivitySetupReader;
import file.StudentClassChoiceReader;
import file.StudentClassScheduleReader;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;


//TODO - logic to fill gaps with extra classes until fill.  Add Kid isSchedFull method

public class SchedulerMain {
	
	private static final Logger LOGGER = Logger.getLogger(SchedulerMain.class);
	
	Map< String,Activity > actCapacityMap = null;
    Map< String,List<StudentActivity> > scheduleMap = null;
	Map< String,List<Integer> > gradeScheduleMap2 = null; // code=grade, value=List grades offered
	Map< String,Boolean> isGradeAcvitivity = null;  // code, True where activity is grade-specific

	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		List<String> argList = Arrays.asList(args);
		SchedulerMain th = new SchedulerMain();
		
		Map<String, Map> scheduleMaps = new ActivitySetupReader().read();
		th.actCapacityMap = scheduleMaps.get(SchedulerConstants.MAP_NAME_ACTIVITY_INFO);
		th.scheduleMap = scheduleMaps.get(SchedulerConstants.MAP_NAME_ACTIVITY_SCHEDULE);
		th.gradeScheduleMap2 = scheduleMaps.get(SchedulerConstants.MAP_NAME_GRADE_ACTIVITY_SCHEDULE);
		th.isGradeAcvitivity = scheduleMaps.get(SchedulerConstants.MAP_NAME_IS_GRADE_ACTIVITY);
		
		if (argList.isEmpty()) {
			LOGGER.info("Beginning full scheduler run.");

			List<Student> studentList = new StudentClassChoiceReader().read();

			new GradeActivityScheduler().schedule(th, studentList);
			new ActivityScheduler().schedule(th, studentList);
		}
		else if (argList.contains("runreports")) {
			LOGGER.info("Beginning report-only run.");
			th.scheduleMap = th.readStudentSchedules();
		}
		
		new ReportManager().runReports(th);
		
		LOGGER.info("Scheduler run complete.");
	}
	

	Map< String,List<StudentActivity> >  readStudentSchedules() {
		StudentClassScheduleReader reader = new StudentClassScheduleReader();
		return reader.read(this);
	}
	
	public Map<String, Activity> getActCapacityMap() {
		return actCapacityMap;
	}

	public void setActCapacityMap(Map<String, Activity> actCapacityMap) {
		this.actCapacityMap = actCapacityMap;
	}

	public Map<String, List<Integer>> getGradeScheduleMap2() {
		return gradeScheduleMap2;
	}

	public void setGradeScheduleMap2(Map<String, List<Integer>> gradeScheduleMap2) {
		this.gradeScheduleMap2 = gradeScheduleMap2;
	}


	public Map<String, List<StudentActivity>> getScheduleMap() {
		return scheduleMap;
	}


	public void setScheduleMap(Map<String, List<StudentActivity>> scheduleMap) {
		this.scheduleMap = scheduleMap;
	}


	public Map<String, Boolean> getIsGradeAcvitivity() {
		return isGradeAcvitivity;
	}


	public void setIsGradeAcvitivity(Map<String, Boolean> isGradeAcvitivity) {
		this.isGradeAcvitivity = isGradeAcvitivity;
	}
}
