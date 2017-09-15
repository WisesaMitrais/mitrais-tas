package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="tr_eligible_participant")
public class EligibleParticipant {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_eligible_participant")
	private long idEligibleParticipant;
	
	@Column (name = "id_training")
	private long idTraining;
	
	@Column (name = "id_user")
	private long idUser;

	public EligibleParticipant() {
		
	}
	
	public EligibleParticipant(long idTraining, long idUser) {
		this.idTraining = idTraining;
		this.idUser = idUser;
	}

	public long getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(long idTraining) {
		this.idTraining = idTraining;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	
}
