package training.admin.system;

import java.util.Date;
import java.util.List;

public class AttendanceData {

	private Long idEnrollment;
	private String userName;
	private List<String> status;
	private List<Date> dates;

	
	
	public Long getIdEnrollment() {
		return idEnrollment;
	}
	public void setIdEnrollment(Long idEnrollment) {
		this.idEnrollment = idEnrollment;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getStatus() {
		return status;
	}
	public void setStatus(List<String> status) {
		this.status = status;
	}
	public List<Date> getDates() {
		return dates;
	}
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	
	
}
