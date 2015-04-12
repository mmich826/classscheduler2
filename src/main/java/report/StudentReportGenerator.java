package report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import classscheduler.Activity;
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

	private List<HourInfo> hourInfo = null;
	
	public String generate(SchedulerMain mainSched) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		hourInfo = generateHourInfo();
		List<StudentReportDto> dtoList = prepareData(mainSched);
		
        Configuration cfg = new Configuration();

        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(FreemarkerrTestApplication.class, "templates");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("title", "Student Report");
		input.put("reportDtos", dtoList);
		
        Template template = cfg.getTemplate("student_report.ftl");

        Writer fileWriter = new FileWriter(new File("student_report.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
		
		return null;
	}

	List<StudentReportDto> prepareData(SchedulerMain mainSched) {
		
		StringBuilder sb = new StringBuilder(5052);

		Map<String,Activity> activityCapacityMap = mainSched.getActCapacityMap();
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
		
		List<StudentReportDto> dtoList = new ArrayList<StudentReportDto>();
//		List<StudentActivity> studentClassList = null;
//		List<Activity> classInfoList = null;
		List<StudentActivityReportDto> studentActivityReportDtoList = null;
		
		int student = 0;
		StudentReportDto dto = new StudentReportDto();
//		dto.setTimes(hourInfo);
		
		String name = "_BEGIN";
		for (StudentActivity studentAct : studentActivities) {	
			 if (name == null || !name.equals(studentAct.getName())) {
				 name = studentAct.getName();
				 
				 if (student == 0) {
					 student = 1;
				 }
				 else if (student == 1) {
//					 dto.setStudentClasses1(studentClassList);
//					 dto.setActivityInfo1(classInfoList); 
					 dto.setStudent1(studentActivityReportDtoList);
					 student++;
				 }
				 else if (student == 2) {
//					 dto.setStudentClasses2(studentClassList);
//					 dto.setActivityInfo2(classInfoList); 
					 dto.setStudent2(studentActivityReportDtoList);
					 
					 dtoList.add(dto);
					 dto = new StudentReportDto();
					 student = 1;
				 }		 
//				 classInfoList = new ArrayList<Activity>();				 
//				 studentClassList = new ArrayList<StudentActivity>();
				 studentActivityReportDtoList = new ArrayList<StudentActivityReportDto>();
			 }
			 
			 String activityCode = studentAct.getAct() + "-" + studentAct.getHour();
			 int hour = Integer.valueOf(studentAct.getHour()) - 1;
			 studentActivityReportDtoList.add(
			 			convertToReportDto(studentAct, activityCapacityMap.get(activityCode), hourInfo.get(hour) )
			 			);
//			 studentClassList.add(studentAct);
//			 String activityCode = studentAct.getAct() + "-" + studentAct.getHour();
//			 classInfoList.add( activityCapacityMap.get(activityCode) );
		}

		return dtoList;
	}
	
	private StudentActivityReportDto convertToReportDto(
			StudentActivity studentActivity, Activity activity, HourInfo hourInfo) {
		StudentActivityReportDto dto = new StudentActivityReportDto();
		
		dto.setName(studentActivity.getName());
		dto.setGrade(studentActivity.getGrade());
		dto.setTeacher(studentActivity.getTeacher());
		dto.setActivityName(studentActivity.getAct());
		dto.setHour(studentActivity.getHour());
		dto.setTime(hourInfo.getTime());
		dto.setActivityLeader(activity.getActivityLeader());
		dto.setLocation(activity.getLocation());
		dto.setAltLocation(activity.getAltLocation());
		
		return dto;
	}
	
	private List<HourInfo>  generateHourInfo() {
		List<HourInfo> hourInfo = new ArrayList<HourInfo>();
		
		hourInfo.add( new HourInfo(1, "8:10-8:45"));
		hourInfo.add( new HourInfo(2, "9:10-9:45"));
		hourInfo.add( new HourInfo(3, "10:10-10:45"));
		hourInfo.add( new HourInfo(4, "11:10-11:45"));
		hourInfo.add( new HourInfo(5, "12:45-1:20"));
		hourInfo.add( new HourInfo(6, "1:25-2:10"));
		
		return hourInfo;
	}
}
