package report;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import classscheduler.SchedulerMain;
import classscheduler.StudentActivity;

public class ActivityReportGenerator implements ReportGenerator {
	
	private static final Logger LOGGER = Logger.getLogger(ActivityReportGenerator.class);

	public String generate(SchedulerMain mainSched) {
		StringBuilder sb = new StringBuilder(5052);
		
		Map< String, List<StudentActivity> > scheduleMap = mainSched.getScheduleMap();
		
		Object[] keys = scheduleMap.keySet().toArray();
		Arrays.sort(keys);
		 
 		 for (Object key : keys) {			
			List<StudentActivity> Students = scheduleMap.get((String)key) ;
			// Set html header
			for (StudentActivity Student : Students) {
				sb.append( (String)key )
					.append("|")
					.append(Student.getName())
					.append("\n");		
			}
			// Set html footer
		}
 		 
 		return sb.toString();
	}
	
}
