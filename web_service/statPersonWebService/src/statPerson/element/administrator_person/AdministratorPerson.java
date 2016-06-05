package statPerson.element.administrator_person;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "administratorPerson")
public class AdministratorPerson implements Serializable  {

	private static final long serialVersionUID = 1L;

	private int id;
	private int idAccount;
	private int idPerson;
	
	public AdministratorPerson(){}

	public AdministratorPerson(int idAccount, int idPerson) {
		super();
		this.idAccount = idAccount;
		this.idPerson = idPerson;
	}
	

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	
	public int getIdAccount() {
		return idAccount;
	}

	@XmlElement
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public int getIdPerson() {
		return idPerson;
	}

	@XmlElement
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAccount;
		result = prime * result + idPerson;
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
		AdministratorPerson other = (AdministratorPerson) obj;
		if (idAccount != other.idAccount)
			return false;
		if (idPerson != other.idPerson)
			return false;
		return true;
	}
}
