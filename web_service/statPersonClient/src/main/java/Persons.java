import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "people")
public class Persons {

    public Persons() {
    }

    public Persons(List<Person> persons) {
        this.persons = persons;
    }

    @XmlElement(name = "Person", type = Person.class)
    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        if (persons == null)
            return "NULL";
        for (Person person : persons)
            System.out.println(person);
        return "";
    }
}
