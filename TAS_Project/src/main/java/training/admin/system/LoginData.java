package training.admin.system;

import java.util.List;

import training.admin.system.model.Role;

public class LoginData {

	private long idUser;
	private String name;
	private List<Role> roles;
	
	public LoginData() {
		
	}
	
	public LoginData(long idUser, String name, List<Role> roles) {
		this.idUser = idUser;
		this.name = name;
		this.roles = roles;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
