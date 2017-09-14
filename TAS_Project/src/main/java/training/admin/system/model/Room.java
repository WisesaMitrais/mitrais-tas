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
@Table (name = "tbm_room")
public class Room {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="id_room")
	private long idRoom;
	
	@Column (name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn (name="id_office")
	private Office idOffice;
	
	public Room() {
		
	}
	
	public Room(long idRoom, String name, Office idOffice) {
		this.idRoom = idRoom;
		this.name = name;
		this.idOffice = idOffice;
	}

	public long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(long idUser) {
		this.idRoom = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Office getIdOffice() {
		return idOffice;
	}

	public void setId_office(Office idOffice) {
		this.idOffice = idOffice;
	}
	
	

}
