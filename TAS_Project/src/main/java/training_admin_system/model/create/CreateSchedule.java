package training_admin_system.model.create;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import training.admin.system.model.Course;
import training.admin.system.model.Room;
import training.admin.system.model.User;

public class CreateSchedule {

	private long idSchedule;
	private Date startDate;
	private Date endDate;
	private Integer capacity;
	private Course course;
	private Room room;
	private Long idTraining;
	private User mainTrainer;
	private Long idBackupTrainer;
	private Boolean periodic;
	private String periodicTime;
	
	private Long idCourse;
	private Long idMaintrainer;
	private Long idRoom;
	
	public long getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(long idSchedule) {
		this.idSchedule = idSchedule;
	}
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Long getIdTraining() {
		return idTraining;
	}
	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
	}
	public User getMainTrainer() {
		return mainTrainer;
	}
	public void setMainTrainer(User mainTrainer) {
		this.mainTrainer = mainTrainer;
	}
	public Long getIdBackupTrainer() {
		return idBackupTrainer;
	}
	public void setIdBackupTrainer(Long idBackupTrainer) {
		this.idBackupTrainer = idBackupTrainer;
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
	public Long getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}
	public Long getIdMaintrainer() {
		return idMaintrainer;
	}
	public void setIdMaintrainer(Long idMaintrainer) {
		this.idMaintrainer = idMaintrainer;
	}
	public Long getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
	}
	
	
}
