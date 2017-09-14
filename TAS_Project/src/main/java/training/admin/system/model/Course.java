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
	
	public Course() {
		
	}
	
	public Course(long idCourse, String name) {
		super();
		this.idCourse = idCourse;
		this.name = name;
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
