package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private long idUser;

	@Column (name = "id_role")
	private long idRole;

	public UserRole() {
		
	}
	
	public UserRole(long idUserRole, long idUser, long idRole) {
		super();
		this.idUserRole = idUserRole;
		this.idUser = idUser;
		this.idRole = idRole;
	}

	public long getIdUserRole() {
		return idUserRole;
	}

	public void setIdUserRole(long idUserRole) {
		this.idUserRole = idUserRole;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}
		
}
