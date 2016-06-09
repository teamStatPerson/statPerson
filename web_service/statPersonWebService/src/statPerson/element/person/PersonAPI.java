package statPerson.element.person;

import java.util.List;

import javax.ws.rs.core.Response;


public interface PersonAPI {
	
	Integer addPerson(Integer  administratorId, String personName);
	
	List<Person> getPersons(Integer  administratorId);
	
	void removePerson(Integer administratorId, Integer personId);

}
