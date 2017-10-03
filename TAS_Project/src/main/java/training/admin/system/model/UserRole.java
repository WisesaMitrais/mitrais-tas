package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name = "tr_user_role")
public class UserRole {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_user_role")
	private long idUserRole;
	
	@Column (name = "id_user")
	private Long idUser;

	@Column (name = "id_role")
	private Long idRole;

	public UserRole() {
		
	}
	
	public long getIdUserRole() {
		return idUserRole;
	}

	public void setIdUserRole(long idUserRole) {
		this.idUserRole = idUserRole;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}



		
}
