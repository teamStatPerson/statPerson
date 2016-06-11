import java.util.List;

public interface PersonAPI {

    Integer addPerson(Integer administratorId, String personName);

    List<Person> getPersons(Integer administratorId);

    List<Person> getAllPersons();

    void removePerson(Integer administratorId, Integer personId);

}
