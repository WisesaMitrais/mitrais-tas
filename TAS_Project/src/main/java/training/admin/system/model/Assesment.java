package training.admin.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_assesment")
public class Assesment {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id_assesment")
	private long id_assesment;
	
	@Column (name="name")
	private String name;
	
	@Column (name="datetime")
	private Date datetime;
	
	public Assesment() {
		
	}

	public Assesment(long id_assesment, String name, Date datetime) {
		super();
		this.id_assesment = id_assesment;
		this.name = name;
		this.datetime = datetime;
	}

	public long getId_assesment() {
		return id_assesment;
	}

	public void setId_assesment(long id_assesment) {
		this.id_assesment = id_assesment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	
}
