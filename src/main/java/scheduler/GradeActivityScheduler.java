package scheduler;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import classscheduler.Activity;
import classscheduler.SchedulerConstants;
import classscheduler.SchedulerMain;
import classscheduler.Student;
import classscheduler.StudentActivity;



public class GradeActivityScheduler {
	
	private static final Logger LOGGER = Logger.getLogger(GradeActivityScheduler.class);

	public void schedule(SchedulerMain mainSched, List<Student> studentList) {
		List<StudentActivity> clazList = null;
		StudentActivity studentAct = null;
		Map<String,Boolean> isGradeActivityMap = mainSched.getIsGradeAcvitivity();
		
		try {
			for (int i = 0; i < SchedulerConstants.NUMBER_OF_ACTIVITIES_TO_SCHEDULE; i++) {  // CLASS PRIORITY
				for(Student student : studentList) {   // STUDENTS
					
					studentAct = new StudentActivity();
					studentAct.setName(student.getName()); 
					try {
						studentAct.setAct(student.getAct().get(i));
					} catch (IndexOutOfBoundsException e) {
						StringBuilder sb = new StringBuilder();
						sb.append(studentAct.getAct()).append("|").append(studentAct.getName()).append("|")
							.append("No activity choice for ").append(i+1);
						LOGGER.warn(sb.toString());
						continue;
					}
					studentAct.setGrade(student.getGrade());
					studentAct.setTeacher(student.getTeacher());
	
					boolean isGradeSpecActivity = isGradeActivityMap.get(studentAct.getAct()) == null ? false : true;
					if (!isGradeSpecActivity) { 
						continue;
					}
					
					boolean isRegistrationSuccess = findOpeningAndSchedule(clazList, studentAct, student, mainSched);
					
					if ( isGradeSpecActivity && !isRegistrationSuccess ){
						StringBuilder sb = new StringBuilder();
						sb.append(studentAct.getAct()).append("|").append(studentAct.getName()).append("|")
							.append("Failed to register student for grade-spec activity choice ").append(i+1);
						LOGGER.warn(sb.toString());
					}
	
				}
			}
		
		} catch (Exception e) {
			LOGGER.warn("Error scheduling:  " + studentAct);
			e.printStackTrace();
		}
	}

	boolean findOpeningAndSchedule(	List<StudentActivity> clazList, 
									StudentActivity studentActivity, 
									Student student, 
									SchedulerMain mainSched) {
		
		boolean isRegistrationSuccess = false;

		for(int i=0; i<SchedulerConstants.NUMBER_OF_ACTIVITIES_TO_SCHEDULE; i++) {   // ACTUAL HOURS	
			
			int hour = i+1;
			String activityCode = studentActivity.getAct() + "-" + hour;
			List<Integer> gradesForClass = mainSched.getGradeScheduleMap2().get(activityCode);
			
			if (gradesForClass == null) {
				// Class not scheduled for this hour
				continue;
			}
			
			for (int j=0; j<gradesForClass.size(); j++) {
				int grade = gradesForClass.get(j);
				if ( Integer.valueOf(student.getGrade()) != grade) continue;  // continue if class for students grade
				
				String actName = studentActivity.getAct() + "-" + hour;
				
				boolean isBooked = student.getActSchedList().get(hour-1);
				if (isBooked) continue;
				
				Activity act = mainSched.getActCapacityMap().get(actName);
				if (act.isFull()) continue;

				clazList = mainSched.getScheduleMap().get(actName);							
				studentActivity.setHour(Integer.toString(hour));
				
				clazList.add(studentActivity);
				act.enrollmentIncr();
				student.getActSchedList().set(i, true);
				isRegistrationSuccess = true;
				break;
			}
			
			if (isRegistrationSuccess) {
				break;
			}
		}
		
		return isRegistrationSuccess;
	}
}
