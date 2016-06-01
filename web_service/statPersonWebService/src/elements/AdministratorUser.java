package elements;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userAdministrator")
public class AdministratorUser implements Serializable  {

	private static final long serialVersionUID = 1L;

	private int idAdministrator;
	private int idUser;
	
	AdministratorUser(){}

	public AdministratorUser(int idAdministrator, int idUser) {
		super();
		this.idAdministrator = idAdministrator;
		this.idUser = idUser;
	}

	public int getIdAdministrator() {
		return idAdministrator;
	}

	@XmlElement
	public void setIdAdministrator(int idAdministrator) {
		this.idAdministrator = idAdministrator;
	}

	public int getIdUser() {
		return idUser;
	}

	@XmlElement
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministratorUser other = (AdministratorUser) obj;
		if (idAdministrator != other.idAdministrator)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
}
