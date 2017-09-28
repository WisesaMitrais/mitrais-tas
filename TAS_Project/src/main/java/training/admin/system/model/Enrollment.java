package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="id_enrollment")
	private long idEnrollment;
		
	@ManyToOne
	@JoinColumn (name="id_schedule")
	private Schedule schedule;
	
	@ManyToOne
	@JoinColumn (name="id_user")
	private User user;

	public Enrollment() {
		
	}
	

	public long getIdEnrollment() {
		return idEnrollment;
	}

	public void setIdEnrollment(long idEnrollment) {
		this.idEnrollment = idEnrollment;
	}


	public Schedule getSchedule() {
		return schedule;
	}


	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
}
