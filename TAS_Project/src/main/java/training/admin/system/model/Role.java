package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tbm_role")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="id_role")
	private long idRole;
	
	@Column (name="role_name")
	private String name;
	
	public Role() {
		
	}
	
	public Role(long idRole, String name) {
		this.idRole = idRole;
		this.name = name;
	}
	
	public long getIdRole() {
		return idRole;
	}
	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
