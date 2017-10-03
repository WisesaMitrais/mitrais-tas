package training.admin.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "tb_schedule")
public class Schedule {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="id_schedule")
	private long idSchedule;
	
	@Column (name="start_date")
	private Date startDate;
	
	@Column (name="end_date")
	private Date endDate;
	
	@Column (name = "capacity")
	private Integer capacity;
		
	@ManyToOne
	@JoinColumn (name = "id_course")
	private Course course;
	
	@ManyToOne
	@JoinColumn (name = "id_room")
	private Room room;
	
	@ManyToOne
	@JoinColumn (name = "id_training")
	private Training training;
	
	@ManyToOne
	@JoinColumn (name = "id_trainer1")
	private User mainTrainer;
	
	@Column (name = "id_trainer2")
	private Long idBackupTrainer;
	
	@Column (name = "periodic")
	private Boolean periodic;
	
	@Column (name = "day")
	private String day;
	
	@Column (name = "hour")
	private String hour;
	
	@Column (name = "schedule_number")
	private Integer scheduleNumber;
	
	private Long createdBy;
	private Date createdAt;
	private Long updatedBy;
	private Date updatedAt;
	
	public Schedule() {
		
	}

	public long getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(long idSchedule) {
		this.idSchedule = idSchedule;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date start_date) {
		this.startDate = start_date;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date end_date) {
		this.endDate = end_date;
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

	public User getMainTrainer() {
		return mainTrainer;
	}

	public void setMainTrainer(User mainTrainer) {
		this.mainTrainer = mainTrainer;
	}

	public Long getIdBackupTrainer() {
		return idBackupTrainer;
	}

	public void setIdBackupTrainer(long idBackupTrainer) {
		this.idBackupTrainer = idBackupTrainer;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Boolean getPeriodic() {
		return periodic;
	}

	public void setPeriodic(Boolean periodic) {
		this.periodic = periodic;
	}

	public Integer getScheduleNumber() {
		return scheduleNumber;
	}

	public void setScheduleNumber(Integer scheduleNumber) {
		this.scheduleNumber = scheduleNumber;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setIdBackupTrainer(Long idBackupTrainer) {
		this.idBackupTrainer = idBackupTrainer;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}
		
	
	
}
