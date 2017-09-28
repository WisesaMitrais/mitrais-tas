package training.admin.system;

import java.util.Date;

public class AddSchedule {
	private Date startDate;
	private Date endDate;
	private Integer capacity;
	private Boolean periodic;
	private String periodicTime;
	private Long idMainTrainer;
	private Long idBackupTrainer;
	private Long idRoom;
	private Long idCourse;
	private Long idTraining;
	private String createdBy;
	private String createdAt;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Boolean getPeriodic() {
		return periodic;
	}
	public void setPeriodic(Boolean periodic) {
		this.periodic = periodic;
	}
	public String getPeriodicTime() {
		return periodicTime;
	}
	public void setPeriodicTime(String periodicTime) {
		this.periodicTime = periodicTime;
	}
	public Long getIdMainTrainer() {
		return idMainTrainer;
	}
	public void setIdMainTrainer(Long idMainTrainer) {
		this.idMainTrainer = idMainTrainer;
	}
	public Long getIdBackupTrainer() {
		return idBackupTrainer;
	}
	public void setIdBackupTrainer(Long idBackupTrainer) {
		this.idBackupTrainer = idBackupTrainer;
	}
	public Long getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
	}
	public Long getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}
	public Long getIdTraining() {
		return idTraining;
	}
	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
