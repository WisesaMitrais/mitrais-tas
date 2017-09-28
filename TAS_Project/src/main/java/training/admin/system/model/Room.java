package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name="id_office")
	private Office office;
	
	@Column (name="detail")
	private String detail;
	
	public Room() {
		
	}
	
	public Room(long idRoom, String name, Office office) {
		this.idRoom = idRoom;
		this.name = name;
		this.office = office;
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

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	

}
