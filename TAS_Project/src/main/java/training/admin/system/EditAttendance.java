package training.admin.system;

import java.util.List;

public class EditAttendance {

	private Long idEnrollment;
	private List<String> status;
	
	public Long getIdEnrollment() {
		return idEnrollment;
	}
	public void setIdEnrollment(Long idEnrollment) {
		this.idEnrollment = idEnrollment;
	}
	public List<String> getStatus() {
		return status;
	}
	public void setStatus(List<String> status) {
		this.status = status;
	}
	
}
