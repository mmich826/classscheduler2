package report;

import java.io.IOException;

import org.apache.log4j.Logger;

import classscheduler.SchedulerMain;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class ReportManager {
	private static final Logger LOGGER = Logger.getLogger(ReportManager.class);

	
	public void runReports(SchedulerMain th) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		String rpt = null;
		
		new FullReportGenerator().generate(th);
		
		rpt = new ActivityReportGenerator().generate(th);

		rpt = new StudentReportGenerator().generate(th);
		
		LOGGER.debug(rpt);
	}

}
