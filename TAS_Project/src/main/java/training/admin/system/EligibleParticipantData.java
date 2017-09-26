package training.admin.system;

public class EligibleParticipantData {

	private Long idUser;
	private Long eligibleNumber;
//	private String trainingName;
	private String name;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long id) {
		this.idUser = id;
	}
//	public String getTrainingName() {
//		return trainingName;
//	}
//	public void setTrainingName(String trainingName) {
//		this.trainingName = trainingName;
//	}
	public String getName() {
		return name;
	}
	public void setName(String userName) {
		this.name = userName;
	}
	public Long getEligibleNumber() {
		return eligibleNumber;
	}
	public void setEligibleNumber(Long eligibleNumber) {
		this.eligibleNumber = eligibleNumber;
	}
	
	
	
	
}
