package statPerson.element.account;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public static int PRIMARY_ADMINISTRATOR_ACCOUNT = 0;
	public static int SECONDARY_ADMINISTRATOR_ACCOUNT = 10;
	public static int USER_ACCOUNT = 100;
	
	public static int DEFAULF_EMPTY_LINKED_ADMINISRATOR = 100;

	private int id;
	private String email;
	private String password;
	private Date dateRegistration;
	private int typeAccount;
	private int idLinkedAdministrator;

	public Account() {
	}
//	
//	public Account(String email, String password, Date dateRegistration) {
//		super();
//		this.email = email;
//		this.password = password;
//		this.dateRegistration = dateRegistration;
//		this.typeAccount = PRIMARY_ADMINISTRATOR_ACCOUNT;
//		this.id_linked_adminitsator = DEFAULF_EMPTY_LINKED_ADMINISRATOR;
//	}

	public Account(String email, String password, Date dateRegistration, int typeAccount, int idLinkedAdministrator) {
		super();
		this.email = email;
		this.password = password;
		this.dateRegistration = dateRegistration;
		this.typeAccount = typeAccount;
		this.idLinkedAdministrator = idLinkedAdministrator;
	}


	public boolean isPrimaryAdministrator(){
		if(typeAccount == PRIMARY_ADMINISTRATOR_ACCOUNT)
			return true;
		return false;
	}
	
	public boolean isUser(){
		if(typeAccount == USER_ACCOUNT)
			return true;
		return false;
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

	public int getTypeAccount() {
		return typeAccount;
	}

	@XmlElement
	public void setTypeAccount(int typeAccount) {
		this.typeAccount = typeAccount;
	}

	public int getIdLinkedAdministrator() {
		return idLinkedAdministrator;
	}

	@XmlElement
	public void setIdLinkedAdministrator(int idLinkedAdministrator) {
		this.idLinkedAdministrator = idLinkedAdministrator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateRegistration == null) ? 0 : dateRegistration.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idLinkedAdministrator;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + typeAccount;
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
		Account other = (Account) obj;
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
		if (idLinkedAdministrator != other.idLinkedAdministrator)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (typeAccount != other.typeAccount)
			return false;
		return true;
	}

}