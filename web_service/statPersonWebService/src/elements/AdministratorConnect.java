package elements;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "administratorConnect")
public class AdministratorConnect implements Serializable  {

	private static final long serialVersionUID = 1L;

	private int idAdministrator;
	private int idAdministratorSecondary;
	
	public AdministratorConnect(){}

	public AdministratorConnect(int idAdministrator, int idAdministratorSecondary) {
		super();
		this.idAdministrator = idAdministrator;
		this.idAdministratorSecondary = idAdministratorSecondary;
	}

	public int getIdAdministrator() {
		return idAdministrator;
	}

	@XmlElement
	public void setIdAdministrator(int idAdministrator) {
		this.idAdministrator = idAdministrator;
	}

	public int getIdAdministratorSecondary() {
		return idAdministratorSecondary;
	}

	@XmlElement
	public void setIdAdministratorSecondary(int idAdministratorSecondary) {
		this.idAdministratorSecondary = idAdministratorSecondary;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministratorConnect other = (AdministratorConnect) obj;
		if (idAdministrator != other.idAdministrator)
			return false;
		if (idAdministratorSecondary != other.idAdministratorSecondary)
			return false;
		return true;
	}
	
}
