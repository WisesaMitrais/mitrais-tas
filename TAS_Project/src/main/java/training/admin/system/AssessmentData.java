package training.admin.system;

public class AssessmentData {
	private Integer number;
	private Long idUser;
	private Long idEnrollment;
	private String name;
	private String status;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdEnrollment() {
		return idEnrollment;
	}
	public void setIdEnrollment(Long idEnrollment) {
		this.idEnrollment = idEnrollment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
