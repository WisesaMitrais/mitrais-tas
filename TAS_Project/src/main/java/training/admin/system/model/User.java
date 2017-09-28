package training.admin.system.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbm_user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column (name = "id_user")
	private long idUser;
	
	@Column (name = "name")
	private String name;

	@Column (name = "email")
	private String email;
	
	@Column (name = "username")
	private String username;

	@Column (name = "password")
	private String password;

	@Column (name = "active")
	private Boolean active;
	
	@Column (name = "job_family_stream")
	private String jobFamilyStream;
	
	@Column (name = "grade")
	private String grade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "id_office")
	private Office office;
	
	public User() {
		
	}
	
	public User(Long idUser, String name, String email, String username, String password, Boolean active, String jobFamilyStream,
			String grade, Office office) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.active = active;
		this.jobFamilyStream = jobFamilyStream;
		this.grade = grade;
		this.office = office;
	}
	
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Boolean getActive() {
		return active;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
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
	public Boolean isActive() {
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
	
	
}
