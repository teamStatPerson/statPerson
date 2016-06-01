package statPerson;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "administrator")
public class Administrator implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String email;
	private String password;
	private Date dateRegistration;
	private boolean secondaryAdministrator;

	public Administrator(){}

	public Administrator(int id, String email, String password, Date dateRegistration, boolean secondaryAdministrator) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.dateRegistration = dateRegistration;
		this.secondaryAdministrator = secondaryAdministrator;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	public void setSecondaryAdministrator(boolean secondaryAdministrator) {
		this.secondaryAdministrator = secondaryAdministrator;
	}

	
	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Date getDateRegistration() {
		return dateRegistration;
	}

	public String getEmail() {
		return email;
	}

	public boolean getSecondaryAdministrator() {
		return secondaryAdministrator;
	}
}