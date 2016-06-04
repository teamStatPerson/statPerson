package statPerson.elements;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "site")
public interface SitesRest extends Serializable {

	@XmlElement
    public void setId(int id);

	@XmlElement
    public void setStartDateStatistics(Calendar startDateStatistics);
	
}
