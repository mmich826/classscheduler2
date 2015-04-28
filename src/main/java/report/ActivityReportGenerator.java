package report;

import static classscheduler.SchedulerConstants.HOUR_1_TEXT;
import static classscheduler.SchedulerConstants.HOUR_2_TEXT;
import static classscheduler.SchedulerConstants.HOUR_3_TEXT;
import static classscheduler.SchedulerConstants.HOUR_4_TEXT;
import static classscheduler.SchedulerConstants.HOUR_5_TEXT;
import static classscheduler.SchedulerConstants.HOUR_6_TEXT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import classscheduler.Activity;
import classscheduler.ActivityStudentComparator;
import classscheduler.FreemarkerrTestApplication;
import classscheduler.SchedulerMain;
import classscheduler.StudentActivity;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class ActivityReportGenerator implements ReportGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(ActivityReportGenerator.class);
	
	private List<HourInfo> hourInfo = null;

	public String generate(SchedulerMain mainSched) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {

		hourInfo = generateHourInfo();
		
		Map<String, List<StudentActivity>> scheduleMap = mainSched
				.getScheduleMap();
		Map<String, Activity> activityMap = mainSched.getActCapacityMap();

		Object[] keys = scheduleMap.keySet().toArray();
		Arrays.sort(keys);

		List<ActivityReportDto> activityDtos = new ArrayList<ActivityReportDto>();
		for (Object key : keys) {
			List<StudentActivity> studentActivities = scheduleMap.get((String) key);
	
			if (studentActivities != null && !studentActivities.isEmpty()) {

				Collections.sort(studentActivities, new ActivityStudentComparator());
				
				activityDtos.add( new ActivityReportDto(
									studentActivities.get(0).getAct(), 
									studentActivities.get(0).getHour(), 
									hourInfo.get( Integer.valueOf(studentActivities.get(0).getHour())-1 ).getTime(),
									studentActivities,
									activityMap.get(
											studentActivities.get(0).getAct() + "-" + studentActivities.get(0).getHour()
												)));
				
			}
		}
		
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(FreemarkerrTestApplication.class,
				"templates");
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("title", "Activity Report");
		input.put("reportDtos", activityDtos);

		Template template = cfg.getTemplate("activity_report.ftl");

		Writer fileWriter = new FileWriter(new File("activity_report.html"));
		try {
			template.process(input, fileWriter);
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			fileWriter.close();
		}

		if (LOGGER.isDebugEnabled()) {
			printReport(scheduleMap, keys);
		}

		return null;
	}

	private void printReport(Map<String, List<StudentActivity>> scheduleMap,
			Object[] keys) {

		StringBuilder sb = new StringBuilder(5052);
		for (Object key : keys) {
			List<StudentActivity> Students = scheduleMap.get((String) key);
			for (StudentActivity Student : Students) {
				sb.append((String) key).append("|").append(Student.getName())
						.append("\n");
			}
		}
	}
	
	//TODO:  Make configurable 
	private List<HourInfo>  generateHourInfo() {
		List<HourInfo> hourInfo = new ArrayList<HourInfo>();
		
		hourInfo.add( new HourInfo(1, HOUR_1_TEXT));
		hourInfo.add( new HourInfo(2, HOUR_2_TEXT));
		hourInfo.add( new HourInfo(3, HOUR_3_TEXT));
		hourInfo.add( new HourInfo(4, HOUR_4_TEXT));
		hourInfo.add( new HourInfo(5, HOUR_5_TEXT));
		hourInfo.add( new HourInfo(6, HOUR_6_TEXT));
		
		return hourInfo;
	}

}
