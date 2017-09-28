package training.admin.system;

import java.util.Date;

import javax.persistence.Column;

public class PeriodData {
	private Long idTraining;
	private String name;
	private String active;
	private Integer Courses;
	private String startDate;
	private String endDate;
	private String createdBy;
	private String updatedBy;
	private Boolean openEnrollment;
	private Boolean bccTraining;
	

	public PeriodData(Long idTraining, String name, String active, Integer courses, String startDate, String endDate,
			String createdBy, String updatedBy, Boolean openEnrollment, Boolean bccTraining) {
		super();
		this.idTraining = idTraining;
		this.name = name;
		this.active = active;
		Courses = courses;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.openEnrollment = openEnrollment;
		this.bccTraining = bccTraining;
	}

	public PeriodData() {
		
	}
	
	public Long getIdTraining() {
		return idTraining;
	}
	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Integer getCourses() {
		return Courses;
	}
	public void setCourses(Integer courses) {
		Courses = courses;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getOpenEnrollment() {
		return openEnrollment;
	}

	public void setOpenEnrollment(Boolean openEnrollment) {
		this.openEnrollment = openEnrollment;
	}

	public Boolean getBccTraining() {
		return bccTraining;
	}

	public void setBccTraining(Boolean bccTraining) {
		this.bccTraining = bccTraining;
	}
		
}
