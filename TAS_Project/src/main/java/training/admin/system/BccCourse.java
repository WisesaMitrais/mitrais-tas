package training.admin.system;

public class BccCourse {

	private String trainer;
	private String mon;
	private String teu;
	private String wed;
	private String thu;
	private String fri;
	
	public BccCourse(String trainer, String mon, String teu, String wed, String thu, String fri) {
		super();
		this.trainer = trainer;
		this.mon = mon;
		this.teu = teu;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
	}
	
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public String getTeu() {
		return teu;
	}
	public void setTeu(String teu) {
		this.teu = teu;
	}
	public String getWed() {
		return wed;
	}
	public void setWed(String wed) {
		this.wed = wed;
	}
	public String getThu() {
		return thu;
	}
	public void setThu(String thu) {
		this.thu = thu;
	}
	public String getFri() {
		return fri;
	}
	public void setFri(String fri) {
		this.fri = fri;
	}
	
	
}
