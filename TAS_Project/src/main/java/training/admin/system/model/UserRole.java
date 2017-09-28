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
	
	@ManyToOne
	@JoinColumn (name = "id_user")
	private User user;

	@ManyToOne
	@JoinColumn (name = "id_role")
	private Role role;

	public UserRole() {
		
	}
	
	public long getIdUserRole() {
		return idUserRole;
	}

	public void setIdUserRole(long idUserRole) {
		this.idUserRole = idUserRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


		
}
