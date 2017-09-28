package training.admin.system;

import java.util.Date;

public class ScheduleData {
	
	private Long idSchedule;
	private String name;
	private String mainTrainer;
	private String backupTrainer;
	private String room;
	private String day;
	private String startDate;
	private String endDate;
	private Integer capacity;
	private Integer participantNumber;
 	private String scheduleType;
	private Boolean periodic;
	private String periodicTime;

	private Long idMainTrainer;
	private Long idBackupTrainer;
	private Long idRoom;
	private Long idCourse;
	private Long idTraining;
	
	private String createdBy;
	private String createdAt;
	private String updatedBy;
	private String updatedAt;
	private String city;
	
	public ScheduleData() {
		
	}
		
	public Long getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(Long idSchedule) {
		this.idSchedule = idSchedule;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMainTrainer() {
		return mainTrainer;
	}
	public void setMainTrainer(String mainTrainer) {
		this.mainTrainer = mainTrainer;
	}
	public String getBackupTrainer() {
		return backupTrainer;
	}
	public void setBackupTrainer(String backupTrainer) {
		this.backupTrainer = backupTrainer;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getStartTime() {
		return startDate;
	}
	public void setStartTime(String startTime) {
		this.startDate = startTime;
	}
	public String getEndTime() {
		return endDate;
	}
	public void setEndTime(String endTime) {
		this.endDate = endTime;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Integer getParticipantNumber() {
		return participantNumber;
	}
	public void setParticipantNumber(Integer participantNumber) {
		this.participantNumber = participantNumber;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String periodic) {
		this.scheduleType = periodic;
	}

	public Long getIdMainTrainer() {
		return idMainTrainer;
	}

	public void setIdMainTrainer(Long idMaintrainer) {
		this.idMainTrainer = idMaintrainer;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
