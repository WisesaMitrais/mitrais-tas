package training.admin.system;

public class UserData {

	private Long idUser;  
	private String name;
	private String email;
	private String jobFamilyStream;
	private String Grade;
	private String accountName;
	private Boolean active;
	private String role;
	
	public UserData() {
		
	}
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobFamilyStream() {
		return jobFamilyStream;
	}
	public void setJobFamilyStream(String jobFamilyStream) {
		this.jobFamilyStream = jobFamilyStream;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
