package statPerson.elements;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public interface PersonsRest extends Serializable {

	@XmlElement
	public void setId(int id);

	@XmlElement
	public void setName(String name);
}
