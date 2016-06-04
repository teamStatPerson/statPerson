package elements;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Keyword{
	

	private int id;
	private String name;
	private int idPerson;
	
	public Keyword(){}

	public Keyword(int id, String name, int idPerson) {
		super();
		this.id = id;
		this.name = name;
		this.idPerson = idPerson;
	}

	public int getId() {
		return id;
	}
 {
		this.id = id;
	}

	public String getName() {
		return name;
	}
 {
		this.name = name;
	}

	public int getIdPerson() {
		return idPerson;
	}
 {
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
		Keyword other = (Keyword) obj;
		if (id != other.id)
			return false;
		if (idPerson != other.idPerson)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
