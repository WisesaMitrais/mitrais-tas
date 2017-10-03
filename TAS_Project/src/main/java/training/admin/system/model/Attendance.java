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
@Table (name="tb_attendance")
public class Attendance {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="id_attendance")
	private long idAttendance;
	
	@Column (name="status")
	private String status;
	
	@Column (name="datetime")
	private String datetime;
	
	@ManyToOne
	@JoinColumn (name="id_enrollment")
	private Enrollment enrollment;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		enrollment = enrollment;
	}

}
