package elements;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String email;
	private String password;
	private Date dateRegistration;

	public User(){}

	public User(int id, String email, String password, Date dateRegistration) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.dateRegistration = dateRegistration;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
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
		return true;
	}
}
