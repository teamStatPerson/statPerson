package statPerson.elements;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import FromCrauler.PersonPageRanks;

public interface PagesRest extends Serializable {

	@XmlElement
	public void setId(int id);

	@XmlElement
	public void setUrl(String url);

	@XmlElement
	public void setSiteId(int siteId);

	@XmlElement
	public void setFoundDateTime(Calendar foundDateTime);

	@XmlElement
	public void setLastScanDate(Calendar lastScanDate);

	@XmlElement
	public void setHtml(String html);

	@XmlElement
	public void setSite(Sites site);

	@XmlElement
    public void setPersonpagerank(Set<PersonPageRanks> personpagerank);
}
