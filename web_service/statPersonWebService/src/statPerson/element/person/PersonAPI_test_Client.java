package statPerson.element.person;

import static org.junit.Assert.assertTrue;
import java.util.List;

import org.junit.Test;

public class PersonAPI_test_Client {

	@Test
	public void testGET() {
		PersonRestClient prc = new PersonRestClient();
		List<Person> persons = prc.getPersons(0);
		assertTrue(persons.size() > 0);
	}

	@Test
	public void testDELETE() {
		PersonRestClient prc = new PersonRestClient();
		int size1 = prc.getPersons(0).size();
		prc.removePerson(0, 0);
		int size2 = prc.getPersons(0).size();
		assertTrue(size1 > size2);
	}

	@Test
	public void testADD() {
		PersonRestClient prc = new PersonRestClient();
		int size1 = prc.getPersons(0).size();
		prc.addPerson(0, "Faust");
		int size2 = prc.getPersons(0).size();
		assertTrue(size1 < size2);
	}
}
