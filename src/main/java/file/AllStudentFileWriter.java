package file;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import classscheduler.SchedulerConstants;

public class AllStudentFileWriter {
	
	private static final Logger LOGGER = Logger.getLogger(AllStudentFileWriter.class);

	String sFileName = SchedulerConstants.STUDENT_SCHEDULE_FILENAME;

	   public void writeFile(String content)
	   {
			try
			{
			    FileWriter writer = new FileWriter(sFileName);
		 
			    writer.append(content);
		 
			    writer.flush();
			    writer.close();
			}
			catch(IOException e)
			{
				LOGGER.error("Error writing " + SchedulerConstants.STUDENT_SCHEDULE_FILENAME + " file:  " + e.getMessage(), e);
			} 
	}
}
