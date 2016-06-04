package statPerson.administrator;

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

	public Administrator(
			String email, 
			String password, 
			Date dateRegistration, 
			boolean secondaryAdministrator) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		if (dateRegistration == null) {
			if (other.dateRegistration != null)
				return false;
		} else if (!dateRegistration.equals(other.dateRegistration))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (secondaryAdministrator != other.secondaryAdministrator)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", email=" + email + ", password=" + password + ", dateRegistration="
				+ dateRegistration + ", secondaryAdministrator=" + secondaryAdministrator + "]";
	}
	
	
	
}