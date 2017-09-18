package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tbm_course")
public class Course {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name="id_course")
	private long idCourse;
	
	@Column(name="name")
	private String name;
	
	@Column(name="bcc_course")
	private Boolean bccCourse;
	
	public Course() {
		
	}
	
	public Boolean isBccCourse() {
		return bccCourse;
	}

	public void setBccCourse(Boolean bccCourse) {
		this.bccCourse = bccCourse;
	}

	public Course(long idCourse, String name, Boolean bccCourse) {
		super();
		this.idCourse = idCourse;
		this.name = name;
		this.bccCourse = bccCourse;
	}
	
	public long getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(long idCourse) {
		this.idCourse = idCourse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
