package training.admin.system;

import java.util.Date;

public class ScheduleData {
	
	private Long idSchedule;
	private String name;
	private String mainTrainer;
	private String backupTrainer;
	private String room;
	private String startDate;
	private String endDate;
	private Date _startDate;
	private Date _endDate;
	private Integer capacity;
	private Integer participantNumber;
 	private String scheduleType;
	private Boolean periodic;
	private String day;
	private String hour;

	private Long _MainTrainer;
	private Long _BackupTrainer;
	private Long _Room;
	private Long _Course;
	private Long _Training;
	
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

	public Long get_MainTrainer() {
		return _MainTrainer;
	}

	public void set_MainTrainer(Long _MainTrainer) {
		this._MainTrainer = _MainTrainer;
	}

	public Long get_BackupTrainer() {
		return _BackupTrainer;
	}

	public void set_BackupTrainer(Long _BackupTrainer) {
		this._BackupTrainer = _BackupTrainer;
	}

	public Long get_Room() {
		return _Room;
	}

	public void set_Room(Long _Room) {
		this._Room = _Room;
	}

	public Long get_Course() {
		return _Course;
	}

	public void set_Course(Long _Course) {
		this._Course = _Course;
	}

	public Long get_Training() {
		return _Training;
	}

	public void set_Training(Long _Training) {
		this._Training = _Training;
	}

	public Boolean getPeriodic() {
		return periodic;
	}

	public void setPeriodic(Boolean periodic) {
		this.periodic = periodic;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String periodicTime) {
		this.day = periodicTime;
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

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

}
