package training.admin.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
		
	@Column (name = "id_course")
	private Long idCourse;
	
	@Column (name = "id_room")
	private Long idRoom;
	
	@Column (name = "id_training")
	private Long idTraining;
	
	@Column (name = "id_trainer1")
	private Long idMainTrainer;
	
	@Column (name = "id_trainer2")
	private Long idBackupTrainer;
	
	@Column (name = "periodic")
	private Boolean periodic;
	
	@Column (name = "periodic_time")
	private String periodicTime;
	
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
	
	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}

	public Long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
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
		
}
