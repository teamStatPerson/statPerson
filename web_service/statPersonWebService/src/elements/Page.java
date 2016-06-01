package elements;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "page")
public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String url;
	private int idSite;
	private Date foundDateTime;
	private Date lastScanDate;
	
	Page(){}

	public Page(int id, String url, int idSite, Date foundDateTime, Date lastScanDate) {
		super();
		this.id = id;
		this.url = url;
		this.idSite = idSite;
		this.foundDateTime = foundDateTime;
		this.lastScanDate = lastScanDate;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}

	public int getIdSite() {
		return idSite;
	}

	@XmlElement
	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}

	public Date getFoundDateTime() {
		return foundDateTime;
	}

	@XmlElement
	public void setFoundDateTime(Date foundDateTime) {
		this.foundDateTime = foundDateTime;
	}

	public Date getLastScanDate() {
		return lastScanDate;
	}

	@XmlElement
	public void setLastScanDate(Date lastScanDate) {
		this.lastScanDate = lastScanDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (foundDateTime == null) {
			if (other.foundDateTime != null)
				return false;
		} else if (!foundDateTime.equals(other.foundDateTime))
			return false;
		if (id != other.id)
			return false;
		if (idSite != other.idSite)
			return false;
		if (lastScanDate == null) {
			if (other.lastScanDate != null)
				return false;
		} else if (!lastScanDate.equals(other.lastScanDate))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
}
