package scheduler;

import java.util.List;

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
	
					List<Integer> gradeActHours = mainSched.getGradeScheduleMap2().get(studentAct.getAct() + "-" + studentAct.getGrade());					
					boolean isGradeSpecActivity = gradeActHours != null && !gradeActHours.isEmpty();
					
					boolean isRegistrationSuccess = findOpeningAndSchedule(clazList, studentAct, student, mainSched, gradeActHours, isGradeSpecActivity);
					
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
									SchedulerMain mainSched, 
									List<Integer> gradeActHours,
									boolean isGradeSpecActivity) {
		boolean isRegistrationSuccess = false;
		
		if (isGradeSpecActivity) {						
			for (int j=0; j<gradeActHours.size(); j++) {
				int hour = gradeActHours.get(j);
				if (student.getActSchedList().get(hour-1)) continue;  // true=Student booked this hour
				
				String actName = studentActivity.getAct() + "-" + hour;
				Activity act = mainSched.getActCapacityMap().get(actName);
				if (act.isFull()) continue;

				clazList = mainSched.getScheduleMap().get(actName);							
				studentActivity.setHour(Integer.toString(hour));
				
				clazList.add(studentActivity);
				act.enrollmentIncr();
				student.getActSchedList().set(hour-1, true);
				isRegistrationSuccess = true;
				break;
			}
		}
		
		return isRegistrationSuccess;
	}
}
