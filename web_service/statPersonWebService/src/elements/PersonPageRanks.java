package elements;

import javax.persistence.*;

@Entity
@Table(name = "personpagerank")
@IdClass(PersonpagerankElement.class)
public class PersonPageRanks {
    private int personId;
    private int pageId;
    private int rank;
    private Persons person;
    private Pages page;

    @Id
    @Column(name = "PersonID", nullable = false, insertable = false, updatable = false)
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Id
    @Column(name = "PageID", nullable = false, insertable = false, updatable = false)
    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Basic
    @Column(name = "Rank", nullable = false)

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @ManyToOne
    @JoinColumn(name = "PersonID")
    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }

    @ManyToOne
    @JoinColumn(name = "PageID")
    public Pages getPage() {
        return page;
    }

    public void setPage(Pages page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonPageRanks that = (PersonPageRanks) o;

        if (personId != that.personId) return false;
        if (pageId != that.pageId) return false;
        if (rank != that.rank) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId;
        result = 31 * result + pageId;
        result = 31 * result + rank;
        return result;
    }
}
