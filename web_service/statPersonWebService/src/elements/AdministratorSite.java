package elements;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "administratorSite")
public class AdministratorSite implements Serializable  {

	private static final long serialVersionUID = 1L;

	private int idAdministrator;
	private int idSite;
	
	public AdministratorSite(){}

	public AdministratorSite(int idAdministrator, int idSite) {
		super();
		this.idAdministrator = idAdministrator;
		this.idSite = idSite;
	}

	public int getIdAdministrator() {
		return idAdministrator;
	}

	@XmlElement
	public void setIdAdministrator(int idAdministrator) {
		this.idAdministrator = idAdministrator;
	}

	public int getIdSite() {
		return idSite;
	}

	@XmlElement
	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministratorSite other = (AdministratorSite) obj;
		if (idAdministrator != other.idAdministrator)
			return false;
		if (idSite != other.idSite)
			return false;
		return true;
	}
	
}
