package training.admin.system;

import java.util.Date;
import java.util.List;

public class AttendanceData {

	private Long idSchedule;
	private Long idUser;
	private String userName;
	private List<String> status;
	private List<Date> dates;
	public Long getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(Long idSchedule) {
		this.idSchedule = idSchedule;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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
