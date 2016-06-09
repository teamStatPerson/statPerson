package statPerson.element.person;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface PersonAPI {
	
	Integer addPerson(Integer  administratorId, String personName);
	
	List<Person> getPersons(Integer  administratorId);
	
	void removePerson(Integer administratorId, Integer personId);

}
