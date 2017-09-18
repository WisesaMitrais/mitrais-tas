package training.admin.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="tb_training")
public class Training {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="id_training")
	private long idTraining;
	
	@Column (name="training_name")
	private String trainingName;

	@Column (name="start_date")
	private Date stratDate;

	@Column (name="end_date")
	private Date endDate;

	@Column (name="active")
	private boolean active;

	@Column (name="created_by")
	private Long createdBy;

	@Column (name="created_date")
	private Date createdDate;

	@Column (name="updated_by")
	private Long updatedBy;

	@Column (name="updated_date")
	private Date updatedDate;
	
	@Column (name="open_enrollment")
	private Boolean openEnrollment;

	public Training() {
		
	}
	
	public Training(long idTraining, String trainingName, Date stratDate, Date endDate, boolean active, Long createdBy,
			Date createdDate, Long updatedBy, Date updatedDate) {
		this.idTraining = idTraining;
		this.trainingName = trainingName;
		this.stratDate = stratDate;
		this.endDate = endDate;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public long getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(long idTraining) {
		this.idTraining = idTraining;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public Date getStratDate() {
		return stratDate;
	}

	public void setStratDate(Date stratDate) {
		this.stratDate = stratDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean issActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isActive() {
		return active;
	}
	
	public Boolean isOpenEnrollment() {
		return openEnrollment;
	}

	public void setOpenEnrollment(Boolean openEnrollment) {
		this.openEnrollment = openEnrollment;
	}
	
	
}
