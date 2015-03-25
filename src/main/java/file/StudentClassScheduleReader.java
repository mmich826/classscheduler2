package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import classscheduler.SchedulerConstants;
import classscheduler.SchedulerMain;
import classscheduler.StudentActivity;


public class StudentClassScheduleReader {
	
	private static final Logger LOGGER = Logger.getLogger(StudentClassScheduleReader.class);

	public Map< String,List<StudentActivity> > read(SchedulerMain mn) {
		
		Map< String,List<StudentActivity> > scheduleMap = mn.getScheduleMap();
		
		String sCurrentLine = null;		
		try (BufferedReader br = new BufferedReader(new FileReader(SchedulerConstants.STUDENT_SCHEDULE_FILENAME)))
		{
 
			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				i++;
				LOGGER.debug(sCurrentLine);
				List<String> tokList = Arrays.asList( sCurrentLine.split("\\,") );
				
				if (tokList == null || tokList.isEmpty() || tokList.get(0) == null || tokList.get(0).isEmpty()) {
					LOGGER.error("Bad line reading file - either blank line or empty name.  Line number " + i);
					continue;
				}

				StudentActivity studentAct = new StudentActivity();
				String clazzHour = null;
				List<String> tokClazzHour = null;
				try {
					clazzHour = tokList.get(0);
					tokClazzHour = Arrays.asList( clazzHour.split("-") );
					studentAct.setAct(tokClazzHour.get(0));
					studentAct.setHour(tokClazzHour.get(1));
					
					studentAct.setName( tokList.get(1) );
					studentAct.setGrade( tokList.get(2) );
					studentAct.setTeacher( tokList.get(3) );
				} catch (Exception e) {
					LOGGER.error("Error reading line.  Field missing:  [" + sCurrentLine + "].  Activity, name, grade and teacher must be present.");
				}
				
				LOGGER.debug(studentAct);
				
				scheduleMap.get(clazzHour).add(studentAct);
			}
 
		} catch (Exception e) {
			LOGGER.error("Error reading line [" + sCurrentLine + "] in " + SchedulerConstants.STUDENT_SCHEDULE_FILENAME + " file to generate reports:  " + e.getMessage(), e);
		} 
 
		return scheduleMap;
	}

}
