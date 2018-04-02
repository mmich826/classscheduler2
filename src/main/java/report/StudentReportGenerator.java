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
	
	public String generate(SchedulerMain mainSched) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		List<StudentReportDto> dtoList = prepareData(mainSched);
		
        Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading( new File("./src/main/java/classscheduler/templates"));
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
		List<StudentActivityReportDto> studentActivityReportDtoList = null;
		
		int student = 0;
		StudentReportDto dto = new StudentReportDto();
		
		String name = "_BEGIN";
		for (StudentActivity studentAct : studentActivities) {	
			 if (name == null || !name.equals(studentAct.getName())) {
				 name = studentAct.getName();
				 
				 if (student == 0) {
					 student = 1;
				 }
				 else if (student == 1) {
					 dto.setStudent1(studentActivityReportDtoList);
					 student++;
				 }
				 else if (student == 2) {
					 dto.setStudent2(studentActivityReportDtoList);
					 
					 dtoList.add(dto);
					 dto = new StudentReportDto();
					 student = 1;
				 }		 
				 studentActivityReportDtoList = new ArrayList<StudentActivityReportDto>();
			 }
			 
			 String activityCode = studentAct.getAct() + "-" + studentAct.getHour();
			 studentActivityReportDtoList.add(
			 			convertToReportDto(studentAct, activityCapacityMap.get(activityCode))
			 			);
		}
		
		dtoList = setLastStudent(studentActivityReportDtoList, dto, dtoList);

		return dtoList;
	}
	
	private List<StudentReportDto> setLastStudent(List<StudentActivityReportDto> studentActivityReportDtoList,
			StudentReportDto dto,
			List<StudentReportDto> dtoList) {
		if (dto.getStudent1() == null) {
			dto.setStudent1(studentActivityReportDtoList);
		}
		else if (dto.getStudent2() == null) {
			dto.setStudent2(studentActivityReportDtoList);
		}
		dtoList.add(dto);
		
		return dtoList;
	}

	private StudentActivityReportDto convertToReportDto(
			StudentActivity studentActivity, Activity activity) {
		StudentActivityReportDto dto = new StudentActivityReportDto();
		
		dto.setName(studentActivity.getName());
		dto.setGrade(studentActivity.getGrade());
		dto.setTeacher(studentActivity.getTeacher());
		dto.setActivityName(activity.getActivityName());
		dto.setHour(studentActivity.getHour());
		dto.setActivityLeader(activity.getActivityLeader());
		dto.setLocation(activity.getLocation());
		dto.setAltLocation(activity.getAltLocation());
		
		return dto;
	}
	
	private void setLastDto() {
		
		//dtoList.add(dto);
		
	}
}
