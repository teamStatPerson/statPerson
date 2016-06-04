package statPerson.elements;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import statPerson.elements.xml.UsersRest;

@Entity
@Table(name = "users")
public class Users implements UsersRest {

	private static final long serialVersionUID = 1L;

	private int id;
	private String email;
	private String password;
	private Date dateRegistration;

	public Users() {
	}

	public Users(int id, String email, String password, Date dateRegistration) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.dateRegistration = dateRegistration;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public int getId() {
		return id;
	}

    @Basic
    @Column(name = "Password", nullable = false, length = 2048)
	public String getPassword() {
		return password;
	}

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateRegistration", nullable = false)
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
		Users other = (Users) obj;
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
