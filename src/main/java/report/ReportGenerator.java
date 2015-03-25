package report;

import java.io.IOException;

import classscheduler.SchedulerMain;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface ReportGenerator {

	public String generate(SchedulerMain mainSched) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException;

}