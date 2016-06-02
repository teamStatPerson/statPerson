package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import elements.Administrator;
import exceptions.AdministratorManyAccounts;
import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;
import statPerson.AdministratorAPI;
import statPerson.Utils;

public class AdministratorAPITest {

	private Administrator administratorInput;
	private Administrator administratorOutput;

	@Before
	public void createTestAdministator() {
		administratorInput = new Administrator("test@test.ru", "test_password", Utils.getCurrentTime(), false);
	}

	@Test
	public void testGetAdministrator() {
		try {
			while (AdministratorAPI.isExistAdministrator(administratorInput.getEmail(),
					administratorInput.getPassword())) {
				AdministratorAPI.removeAdministrator(administratorInput.getEmail(), administratorInput.getPassword());
			}
		} catch (NotCorrectInputData e1) {
			e1.printStackTrace();
		}
		try {
			administratorOutput = AdministratorAPI.addPrimaryAdministrator(administratorInput.getEmail(),
					administratorInput.getPassword());

		} catch (AdministratorManyAccounts e) {
			e.printStackTrace();
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}

		Assert.assertTrue(administratorInput.getEmail().equals(administratorOutput.getEmail()));
		Assert.assertTrue(administratorInput.getPassword().equals(administratorOutput.getPassword()));
	}

	@After
	public void removeTestAdministrator() {
		try {
			AdministratorAPI.removeAdministrator(administratorOutput.getEmail(),administratorOutput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
	}

}
