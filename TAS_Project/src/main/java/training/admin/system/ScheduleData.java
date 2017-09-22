package training.admin.system;

public class ScheduleData {
	
	private Long idSchedule;
	private String name;
	private String mainTrainer;
	private String backupTrainer;
	private String room;
	private String day;
	private String startTime;
	private String endTime;
	private Integer capacity;
	private Integer participantNumber;
	
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
		
}
