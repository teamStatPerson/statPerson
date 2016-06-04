package statPerson.elements.xml;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public interface UsersRest extends Serializable {

	@XmlElement
	public void setId(int id);

	@XmlElement
	public void setPassword(String password);

	@XmlElement
	public void setDateRegistration(Date dateRegistration);

	@XmlElement
	public void setEmail(String email);
}
