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
@Table (name="tr_eligible_participant")
public class EligibleParticipant {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_eligible_participant")
	private long idEligibleParticipant;
	
	@ManyToOne
	@JoinColumn (name = "id_training")
	private Training training;
	
	@ManyToOne
	@JoinColumn (name = "id_user")
	private User user;

	public EligibleParticipant() {
		
	}
	

	
	public Training getTraining() {
		return training;
	}



	public void setTraining(Training training) {
		this.training = training;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public long getIdEligibleParticipant() {
		return idEligibleParticipant;
	}

	public void setIdEligibleParticipant(long idEligibleParticipant) {
		this.idEligibleParticipant = idEligibleParticipant;
	}
	
	
	
	
}
