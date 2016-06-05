package statPerson.elements;

public class PersonPageRanks {
    private int personId;
    private int pageId;
    private int rank;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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
