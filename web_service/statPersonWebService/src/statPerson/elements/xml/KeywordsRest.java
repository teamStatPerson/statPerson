package statPerson.elements.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "keyword")
public interface KeywordsRest extends Serializable {

	@XmlElement
	public void setName(String name);

	@XmlElement
	public void setPersonId(int idPerson);

	@XmlElement
	public void setId(int id);
}
