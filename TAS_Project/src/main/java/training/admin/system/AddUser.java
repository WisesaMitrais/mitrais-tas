package training.admin.system;


import java.util.List;

import training.admin.system.model.Office;

public class AddUser {
	private String name;
	private String email;
	private String username;
	private String password;
	private Boolean active;
	private String jobFamilyStream;
	private String grade;
	private Long idOffice;
	private List<String> roles;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getJobFamilyStream() {
		return jobFamilyStream;
	}
	public void setJobFamilyStream(String jobFamilyStream) {
		this.jobFamilyStream = jobFamilyStream;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Long getIdOffice() {
		return idOffice;
	}
	public void setIdOffice(Long idOffice) {
		this.idOffice = idOffice;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
