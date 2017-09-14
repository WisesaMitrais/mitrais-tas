package training.admin.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbm_office")
public class Office {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_office")
	private long idOffice;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;
	
	public Office() {
		
	}
	
	public Office(Long idOffice, String name, String address, String city) {
		this.name = name;
		this.address = address;
		this.city = city;
	}
	
	
	public long getIdOffice() {
		return idOffice;
	}

	public void setId(long idOffice) {
		this.idOffice = idOffice;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
