package elements;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "administratorPerson")
public class AdministratorPerson implements Serializable  {

	private static final long serialVersionUID = 1L;

	private int idAdministrator;
	private int idPerson;
	
	public AdministratorPerson(){}

	public AdministratorPerson(int idAdministrator, int idPerson) {
		super();
		this.idAdministrator = idAdministrator;
		this.idPerson = idPerson;
	}

	public int getIdAdministrator() {
		return idAdministrator;
	}

	@XmlElement
	public void setIdAdministrator(int idAdministrator) {
		this.idAdministrator = idAdministrator;
	}
	
	public int getIdPerson() {
		return idPerson;
	}

	@XmlElement
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
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
		if (idAdministrator != other.idAdministrator)
			return false;
		if (idPerson != other.idPerson)
			return false;
		return true;
	}
	
}
