package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import static classscheduler.SchedulerConstants.*;
import classscheduler.Student;


public class StudentClassChoiceReader {
	
	private static final Logger LOGGER = Logger.getLogger(StudentClassChoiceReader.class);

	
	public List<Student> read() {
		
		List<Student> studentList = new ArrayList<Student>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_ACTIVITY_SELECTIONS_FILENAME)))
		{
			String sCurrentLine;
			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				i++;
				LOGGER.debug(sCurrentLine);
				List<String> tokList = Arrays.asList( sCurrentLine.split(STUDENT_SCHEDULE_FILE_DELIMITER) );
				
				if (tokList == null || tokList.isEmpty() || tokList.get(0) == null || tokList.get(0).isEmpty()) {
					LOGGER.error("Bad line reading file - either blank line or empty name.  Line number " + i);
					continue;
				}
				
				Student k = new Student();
				k.setName( tokList.get(0) + "; " + tokList.get(1) );
				k.setGrade( tokList.get(2) );
				k.setTeacher( tokList.get(3) );
				k.setAct( tokList.subList(4, tokList.size()) );

				studentList.add(k);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
 
		return studentList;
	}

}
