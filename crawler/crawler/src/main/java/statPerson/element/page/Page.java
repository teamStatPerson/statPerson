package statPerson.element.page;


import java.util.Calendar;
import java.util.Date;

public class Page{

    private int id;
    private String url;
    private int siteId;
    private Date foundDateTime;
    private Date lastScanDate;
    private String html;

    public Page(){}

	public Page(String url, int siteId, 
			Date foundDateTime, Date lastScanDate,
			String html) {
		super();
		this.url = url;
		this.siteId = siteId;
		this.foundDateTime = foundDateTime;
		this.lastScanDate = lastScanDate;
		this.html = html;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public Date getFoundDateTime() {
		return foundDateTime;
	}

	public void setFoundDateTime(Date foundDateTime) {
		this.foundDateTime = foundDateTime;
	}

	public Date getLastScanDate() {
		return lastScanDate;
	}

	public void setLastScanDate(Date lastScanDate) {
		this.lastScanDate = lastScanDate;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foundDateTime == null) ? 0 : foundDateTime.hashCode());
		result = prime * result + ((html == null) ? 0 : html.hashCode());
		result = prime * result + ((lastScanDate == null) ? 0 : lastScanDate.hashCode());
		result = prime * result + siteId;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
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
		if (html == null) {
			if (other.html != null)
				return false;
		} else if (!html.equals(other.html))
			return false;
		if (lastScanDate == null) {
			if (other.lastScanDate != null)
				return false;
		} else if (!lastScanDate.equals(other.lastScanDate))
			return false;
		if (siteId != other.siteId)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
    
	
}
