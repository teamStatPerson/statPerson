package statPerson.crauler.elements.main.java.entity;

import javax.persistence.*;


/**
 * Created by alexey_n on 01.06.2016.
 */
@Entity
@Table(name = "keywords")
public class Keywords {
    private int id;
    private String name;
    private int personId;
    private Persons person;

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

    @Basic
    @Column(name = "PersonID", nullable = false)
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @ManyToOne
    @JoinColumn(name = "personId")
    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keywords keywords = (Keywords) o;

        if (id != keywords.id) return false;
        if (personId != keywords.personId) return false;
        if (name != null ? !name.equals(keywords.name) : keywords.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + personId;
        return result;
    }
}
