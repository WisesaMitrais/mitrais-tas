package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column (name = "id_user")
	private long idUser;
	
	@Column (name = "id_course")
	private long idCourse;
	
	@Column (name = "id_training")
	private long idTraining;

	public Achievement() {
		
	}
	
	
	public Achievement(long idAchievement, String status, long idUser, long idCourse, long idTraining) {
		this.idAchievement = idAchievement;
		this.status = status;
		this.idUser = idUser;
		this.idCourse = idCourse;
		this.idTraining = idTraining;
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

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(long idCourse) {
		this.idCourse = idCourse;
	}

	public long getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(long idTraining) {
		this.idTraining = idTraining;
	}
	
	
	
	
	
}
