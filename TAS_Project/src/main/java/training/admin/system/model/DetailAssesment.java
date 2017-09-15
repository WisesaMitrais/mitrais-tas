package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tr_detail_assesment")
public class DetailAssesment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_detail_assesmentt")
	private long idDetailAssesment;
	
	@Column (name = "id_assesment")
	private long idAssesment;
	
	@Column (name = "id_enrollement")
	private long idEnrollment;
	
	@Column (name = "score")
	private Float score;

	public DetailAssesment() {
		
	}
	
	public DetailAssesment(long idAssesment, long idEnrollment, Float score) {
		super();
		this.idAssesment = idAssesment;
		this.idEnrollment = idEnrollment;
		this.score = score;
	}

	public long getIdAssesment() {
		return idAssesment;
	}

	public void setIdAssesment(long idAssesment) {
		this.idAssesment = idAssesment;
	}

	public long getIdEnrollment() {
		return idEnrollment;
	}

	public void setIdEnrollment(long idEnrollment) {
		this.idEnrollment = idEnrollment;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	
	
}
