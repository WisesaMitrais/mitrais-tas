package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="id_enrollment")
	private long idEnrollment;
	
	@Column (name="status")
	private String status;
	
	@Column (name="id_schedule")
	private long idSchedule;
	
	@Column (name="id_user")
	private long idUser;

	public Enrollment() {
		
	}
	
	public Enrollment(long idEnrollment, String status, long idSchedule, long idUser) {
		this.idEnrollment = idEnrollment;
		this.status = status;
		this.idSchedule = idSchedule;
		this.idUser = idUser;
	}

	public long getIdEnrollment() {
		return idEnrollment;
	}

	public void setIdEnrollment(long idEnrollment) {
		this.idEnrollment = idEnrollment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(long idSchedule) {
		this.idSchedule = idSchedule;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	
}
