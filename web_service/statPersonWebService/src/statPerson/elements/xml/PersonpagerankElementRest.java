package statPerson.elements.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personpagerank")
public interface PersonpagerankElementRest  extends Serializable {

	@XmlElement
    public void setPersonId(int personId);
	
	@XmlElement
    public void setPageId(int pageId);
}
