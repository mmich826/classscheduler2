package classscheduler;
import java.util.Comparator;


public class ActivityStudentComparator implements Comparator<StudentActivity> {
	
	@Override
	public int compare(StudentActivity o1, StudentActivity o2) {
		
		if (o1 == o2) {
			return 0;
		}
		if (o1 == null) {
			return 1;
		}
		
		return o1.getName().compareTo(o2.getName());
	}

}
