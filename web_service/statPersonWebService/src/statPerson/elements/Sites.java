package entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexey_n on 01.06.2016.
 */
@Entity
@Table(name = "sites")
public class Sites {
    private int id;
    private String name;
    private Calendar StartDateStatistics;
    private Set<Pages> sites = new HashSet<Pages>();

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
    @Column(name = "Name", nullable = false, length = 256)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "StartDateStatistics", nullable = true)
    public Calendar getStartDateStatistics() {
        return StartDateStatistics;
    }

    public void setStartDateStatistics(Calendar startDateStatistics) {
        StartDateStatistics = startDateStatistics;
    }

    @OneToMany(targetEntity = Pages.class, mappedBy = "site")
    public Set<Pages> getSites() {
        return sites;
    }

    public void setSites(Set<Pages> sites) {
        this.sites = sites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sites sites = (Sites) o;

        if (id != sites.id) return false;
        if (name != null ? !name.equals(sites.name) : sites.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
