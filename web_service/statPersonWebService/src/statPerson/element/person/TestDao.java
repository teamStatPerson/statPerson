package statPerson.element.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestDao {

	@Test
	public void test() {
		Person in = new Person("nnnnnn");
		Integer id = PersonDao.addPerson(in.getName());
		assertNotNull(id);
		Person out = PersonDao.getPerson(id);
		assertNotNull(out);
		assertEquals(in.getName(), out.getName());
		PersonDao.removePerson(id);
	}
}
