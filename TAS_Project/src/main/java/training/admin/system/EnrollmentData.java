package training.admin.system;

public class EnrollmentData {

	private Long idEnrollment; 
	private String periodName;
	private String courseName;
	private String trainer;
	private String startTime;
	private String endTime;
	private String status;
	private String userName;
	private Long userNumber;
	
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getIdEnrollment() {
		return idEnrollment;
	}
	public void setIdEnrollment(Long idEnrollment) {
		this.idEnrollment = idEnrollment;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String name) {
		this.userName = name;
	}
	public Long getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(Long userNumber) {
		this.userNumber = userNumber;
	}
	
	
}
