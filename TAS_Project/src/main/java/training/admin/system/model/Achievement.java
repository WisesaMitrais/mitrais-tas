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
@Table (name = "tb_achievement")
public class Achievement {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column (name = "id_achievment")
	private long idAchievement;
	
	@Column (name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn (name = "id_user")	
	private User user;

	@ManyToOne
	@JoinColumn (name = "id_course")
	private Course course;
	
	@ManyToOne
	@JoinColumn (name = "id_training")
	private Training training;

	public Achievement() {
		
	}
	
	public long getIdAchievement() {
		return idAchievement;
	}

	public void setIdAchievement(long idAchievement) {
		this.idAchievement = idAchievement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}
	
}
