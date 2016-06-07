package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexey_n on 01.06.2016.
 */
@Entity
@Table(name = "pages")
public class Pages {
    private int id;
    private String url;
    private int siteId;
    private Date foundDateTime;
    private Date lastScanDate;
    private String html;
    private Sites site;
    private Set<Personpagerank> personpagerank = new HashSet<Personpagerank>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Url", nullable = false, length = 2048)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "SiteID", nullable = false)
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FoundDateTime", nullable = true)
    public Date getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(Date foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastScanDate", nullable = true)
    public Date getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(Date lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    @Basic
    @Column(name = "HTML", nullable = true, length = 36777215)
    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @ManyToOne
    @JoinColumn(name = "SiteID", insertable = false, updatable = false)
    public Sites getSite() {
        return site;
    }

    public void setSite(Sites site) {
        this.site = site;
    }

    @OneToMany(targetEntity = Personpagerank.class, mappedBy = "page")
    public Set<Personpagerank> getPersonpagerank() {
        return personpagerank;
    }

    public void setPersonpagerank(Set<Personpagerank> personpagerank) {
        this.personpagerank = personpagerank;
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
