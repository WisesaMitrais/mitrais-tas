package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column (name="id_enrollment")
	private long idEnrollment;

	public Attendance(long idAttendance, String status, String datetime, long idEnrollment) {
		
		this.idAttendance = idAttendance;
		this.status = status;
		this.datetime = datetime;
		this.idEnrollment = idEnrollment;
	}

	public long getIdAttendance() {
		return idAttendance;
	}

	public void setIdAttendance(long idAttendance) {
		this.idAttendance = idAttendance;
	}

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

	public long getIdEnrollment() {
		return idEnrollment;
	}

	public void setIdEnrollment(long idEnrollment) {
		this.idEnrollment = idEnrollment;
	}
	
	
	
	
	
}
