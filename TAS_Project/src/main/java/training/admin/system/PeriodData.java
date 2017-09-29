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

	private Date _startDate;
	private Date _endDate;
	

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

	public Date get_startDate() {
		return _startDate;
	}

	public void set_startDate(Date _startDate) {
		this._startDate = _startDate;
	}

	public Date get_endDate() {
		return _endDate;
	}

	public void set_endDate(Date _endDate) {
		this._endDate = _endDate;
	}
			
}
