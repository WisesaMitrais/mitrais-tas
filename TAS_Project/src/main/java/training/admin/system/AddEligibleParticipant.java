package training.admin.system;

import java.util.List;

public class AddEligibleParticipant {
	
	private Long idTraining;
	private List<Long> idUser;
	
	public Long getIdTraining() {
		return idTraining;
	}
	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
	}
	public List<Long> getIdUser() {
		return idUser;
	}
	public void setIdUser(List<Long> idUser) {
		this.idUser = idUser;
	}
	
}
