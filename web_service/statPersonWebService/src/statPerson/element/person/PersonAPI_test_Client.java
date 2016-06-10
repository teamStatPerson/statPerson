package statPerson.element.person;

import static org.junit.Assert.assertTrue;
import java.util.List;

import org.junit.Test;

import exceptions.NotCorrectInputData;
import statPerson.element.account.AccountDao;

public class PersonAPI_test_Client {

	@Test
	public void testGET() {
		PersonAPI_REST_Client prc = new PersonAPI_REST_Client();
		List<Person> persons = prc.getPersons(0);
		assertTrue(persons.size() > 0);
	}

	@Test
	public void testDELETE() {
		PersonAPI_REST_Client prc = new PersonAPI_REST_Client();
		int size1 = prc.getPersons(0).size();
		prc.removePerson(0, 0);
		int size2 = prc.getPersons(0).size();
		assertTrue(size1 > size2);
	}

	@Test
	public void testADD() {
		PersonAPI_REST_Client prc = new PersonAPI_REST_Client();
		int size1 = prc.getPersons(0).size();
		prc.addPerson(0, "Faust");
		int size2 = prc.getPersons(0).size();
		assertTrue(size1 < size2);
	}
	
	@Test
	public void demo() {
		Integer id = null;
		try {
			 id = AccountDao.addPrimaryAdministrator("demo", "demo");
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
		
		PersonAPI_REST_Client prc = new PersonAPI_REST_Client();
		Integer id1 = prc.addPerson(id, "Faust1");
		Integer id2 = prc.addPerson(id, "Faust2");
		Integer id3 = prc.addPerson(id, "Faust3");
		Integer id4 = prc.addPerson(id, "Faust4");
		List<Person> persons = prc.getPersons(id);
		System.out.println(persons.size());
		for(int i=0;i<persons.size();i++)
			System.out.println(persons.get(i).getName());
		assertTrue(persons.size() > 0);
	}
}
