package report;

import java.util.List;

public class StudentReportDto {
	List<StudentActivityReportDto> student1;
	List<StudentActivityReportDto> student2;
	
	
	public List<StudentActivityReportDto> getStudent1() {
		return student1;
	}
	public void setStudent1(List<StudentActivityReportDto> student1) {
		this.student1 = student1;
	}
	public List<StudentActivityReportDto> getStudent2() {
		return student2;
	}
	public void setStudent2(List<StudentActivityReportDto> student2) {
		this.student2 = student2;
	}
}
