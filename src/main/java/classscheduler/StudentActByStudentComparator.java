package classscheduler;
import java.util.Comparator;

import org.apache.log4j.Logger;


public class StudentActByStudentComparator implements Comparator<StudentActivity> {
	
	private static final Logger LOGGER = Logger.getLogger(StudentActByStudentComparator.class);

	@Override
	public int compare(StudentActivity o1, StudentActivity o2) {
		if (o1 == o2) {
			return 0;
		}
		else if (o1.getGrade().compareTo(o2.getGrade()) == 1) {
			return 1;
		}
		else if (o1.getGrade().compareTo(o2.getGrade()) == -1) {
			return -1;
		}
		
		// Same Grade
		if (o1.getTeacher().compareTo(o2.getTeacher()) == 1) {
			return 1;
		}
		else if (o1.getTeacher().compareTo(o2.getTeacher()) == -1) {
			return -1;
		}
		
		// Same Grade and teacher
		return o1.getName().compareTo(o2.getName());
	}

}
