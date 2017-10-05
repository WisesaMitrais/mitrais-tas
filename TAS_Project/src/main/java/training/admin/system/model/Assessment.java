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
@Table(name="tb_assessment")
public class Assessment {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_assessment")
	private long id_assessment;
	
	@ManyToOne
	@JoinColumn(name="id_enrollment")
	private Enrollment enrollment;

	@Column(name="status")
	private String status;
	
	
	public long getId_assessment() {
		return id_assessment;
	}

	public void setId_assessment(long id_assessment) {
		this.id_assessment = id_assessment;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
