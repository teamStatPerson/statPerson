package statPerson.elements;


import java.util.Calendar;

public class Pages{

    private int id;
    private String url;
    private int siteId;
    private Calendar foundDateTime;
    private Calendar lastScanDate;
    private String html;
    private Sites site;

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

    public Calendar getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(Calendar foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public Calendar getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(Calendar lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Sites getSite() {
        return site;
    }

    public void setSite(Sites site) {
        this.site = site;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pages pages = (Pages) o;

        if (id != pages.id) return false;
        if (siteId != pages.siteId) return false;
        if (url != null ? !url.equals(pages.url) : pages.url != null) return false;
        if (foundDateTime != null ? !foundDateTime.equals(pages.foundDateTime) : pages.foundDateTime != null)
            return false;
        if (lastScanDate != null ? !lastScanDate.equals(pages.lastScanDate) : pages.lastScanDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + siteId;
        result = 31 * result + (foundDateTime != null ? foundDateTime.hashCode() : 0);
        result = 31 * result + (lastScanDate != null ? lastScanDate.hashCode() : 0);
        return result;
    }
}
