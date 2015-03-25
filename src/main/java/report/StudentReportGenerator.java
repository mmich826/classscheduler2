package report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import classscheduler.FreemarkerrTestApplication;
import classscheduler.SchedulerMain;
import classscheduler.StudentActByStudentComparator;
import classscheduler.StudentActivity;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class StudentReportGenerator implements ReportGenerator {
	
	private static final Logger LOGGER = Logger.getLogger(StudentReportGenerator.class);

	public String generate(SchedulerMain mainSched) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		prepareData(mainSched);
		
        Configuration cfg = new Configuration();

        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(FreemarkerrTestApplication.class, "templates");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = cfg.getTemplate("student_report.ftl");

        Writer fileWriter = new FileWriter(new File("output.html"));
        try {
            template.process(null, fileWriter);
        } finally {
            fileWriter.close();
        }
		
		return null;
	}

	void prepareData(SchedulerMain mainSched) {
		
		StringBuilder sb = new StringBuilder(5052);

		Map< String,List<StudentActivity> > scheduleMap = mainSched.getScheduleMap();
		List<StudentActivity> StudentActivityList = new ArrayList<StudentActivity>();
		
		Iterator<String> iter = scheduleMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			List<StudentActivity> Students = scheduleMap.get(key) ;
			for (StudentActivity Student : Students) {
				//System.out.println(key + "|" + Student.name);
				StudentActivityList.add(Student);
			}
		}
		
		StudentActivity[] studentActivities = new StudentActivity[StudentActivityList.size()];
		StudentActivityList.toArray(studentActivities);
		Arrays.sort(studentActivities, new StudentActByStudentComparator() );
		 
		String name = "_BEGIN";
		for (StudentActivity studentAct : studentActivities) {	
			 if (name == null || !name.equals(studentAct.getName()) ) {
				 name = studentAct.getName();
				 // If not first, set footer hnml
				 // Set header html
			 }
		}

	}
}
