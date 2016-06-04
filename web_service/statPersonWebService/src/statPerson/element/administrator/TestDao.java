package statPerson.element.administrator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;
import statPerson.Utils;

public class TestDao {

	private Administrator administratorInput;
	private Administrator administratorOutput;
	AdministratorDao administratorDAO;

	@Before
	public void createTestAdministator() {
		administratorDAO = new AdministratorDao();
		administratorInput = new Administrator("test@test.ru", "test_password", Utils.getCurrentTime(), false);
	}

	@Test
	public void testDAO() {
		try {
			Assert.assertNull(
					administratorDAO.getAdministrator(administratorInput.getEmail(), administratorInput.getPassword()));
		} catch (AdministratorNotExist e1) {
			try {
				administratorOutput = administratorDAO.addPrimaryAdministrator(administratorInput.getEmail(),
						administratorInput.getPassword());
			} catch (NotCorrectInputData e) {
				e.printStackTrace();
			}

			Assert.assertTrue(administratorInput.getEmail().equals(administratorOutput.getEmail()));
			Assert.assertTrue(administratorInput.getPassword().equals(administratorOutput.getPassword()));
		} catch (NotCorrectInputData e1) {
			e1.printStackTrace();
		}
	}

	@After
	public void removeTestAdministrator() {
		try {
			administratorDAO.removeAdministrator(administratorOutput.getEmail(), administratorOutput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
	}

}
