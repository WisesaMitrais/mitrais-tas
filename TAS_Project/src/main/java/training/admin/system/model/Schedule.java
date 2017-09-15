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
	private Date start_date;
	
	@Column (name="end_date")
	private Date end_date;
	
	@Column (name = "capacity")
	private Integer capacity;
	
	@Column (name= "id_training")
	private Long idTraining;
	
	@Column (name = "id_course")
	private Long idCourse;
	
	@Column (name = "id_room")
	private Long idRoom;
	
	public Schedule() {
		
	}

	public Schedule(long idSchedule, Date start_date, Date end_date, Integer capacity, Long idTraining, Long idCourse,
			Long idRoom) {
		this.idSchedule = idSchedule;
		this.start_date = start_date;
		this.end_date = end_date;
		this.capacity = capacity;
		this.idTraining = idTraining;
		this.idCourse = idCourse;
		this.idRoom = idRoom;
	}

	public long getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(long idSchedule) {
		this.idSchedule = idSchedule;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Long getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
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
	
	
	

}
