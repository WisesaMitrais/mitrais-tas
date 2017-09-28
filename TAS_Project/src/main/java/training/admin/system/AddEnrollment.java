package training.admin.system;

import java.util.List;

public class AddEnrollment {

	private Long idSchedule;
	private List<Long> idUser;
	
	public Long getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(Long idSchedule) {
		this.idSchedule = idSchedule;
	}
	public List<Long> getIdUser() {
		return idUser;
	}
	public void setIdUser(List<Long> idUser) {
		this.idUser = idUser;
	}
}
