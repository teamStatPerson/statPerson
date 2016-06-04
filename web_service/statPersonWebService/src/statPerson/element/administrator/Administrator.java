package statPerson.element.administrator;

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

	public Administrator() {
	}

	public Administrator(String email, String password, Date dateRegistration, boolean secondaryAdministrator) {
		this.email = email;
		this.password = password;
		this.dateRegistration = dateRegistration;
		this.secondaryAdministrator = secondaryAdministrator;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public String getEmail() {
		return email;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateRegistration() {
		return dateRegistration;
	}

	@XmlElement
	public void setSecondaryAdministrator(boolean secondaryAdministrator) {
		this.secondaryAdministrator = secondaryAdministrator;
	}

	public boolean getSecondaryAdministrator() {
		return secondaryAdministrator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateRegistration == null) ? 0 : dateRegistration.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (secondaryAdministrator ? 1231 : 1237);
		return result;
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (secondaryAdministrator != other.secondaryAdministrator)
			return false;
		return true;
	}

}