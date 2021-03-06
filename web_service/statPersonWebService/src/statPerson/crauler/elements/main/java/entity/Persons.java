package statPerson.crauler.elements.main.java.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexey_n on 01.06.2016.
 */
@Entity
@Table(name = "persons")
public class Persons {
    private int id;
    private String name;
    private Set<Keywords> keywords = new HashSet<Keywords>();
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
    @Column(name = "Name", nullable = false, length = 2048)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Keywords.class, mappedBy = "person")
    public Set<Keywords> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<Keywords> keywords) {
        this.keywords = keywords;
    }

    @OneToMany(targetEntity = Personpagerank.class, mappedBy = "person")
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

        Persons persons = (Persons) o;

        if (id != persons.id) return false;
        if (name != null ? !name.equals(persons.name) : persons.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
