package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by alexey_n on 01.06.2016.
 */

public class PersonpagerankPK implements Serializable {
    private int personId;
    private int pageId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonpagerankPK that = (PersonpagerankPK) o;

        if (personId != that.personId) return false;
        if (pageId != that.pageId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId;
        result = 31 * result + pageId;
        return result;
    }
}
