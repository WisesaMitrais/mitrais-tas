package training.admin.system;

public class ActiveTraining {

	private String courseName;
	private String mainTrainer;
	private String backupTrainer;
	private String startDate;
	private String endDate;
	private String office;
	
	public ActiveTraining() {
		
	}
	
	public ActiveTraining(String courseName, String mainTrainer, String backupTrainer, String startDate, String endDate,
			String office) {
		super();
		this.courseName = courseName;
		this.mainTrainer = mainTrainer;
		this.backupTrainer = backupTrainer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.office = office;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	
	
}
