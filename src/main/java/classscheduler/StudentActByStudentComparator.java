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
		if (o1 == null) {
			return 1;
		}
		
		int compareResult = o1.getGrade().compareTo(o2.getGrade());
		if (compareResult != 0) {
			return compareResult;
		}
		
		// Same Grade
		compareResult = o1.getTeacher().compareTo(o2.getTeacher());
		if (compareResult != 0) {
			return compareResult;
		}

		// Same Grade and teacher
		compareResult = o1.getName().compareTo(o2.getName());
		if (compareResult != 0) {
			return compareResult;
		}
		
		return o1.getHour().compareTo(o2.getHour());
	}

}
