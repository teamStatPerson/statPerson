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
	private Integer idAdministratorOutput = null;

	@Before
	public void createTestAdministator() {
		administratorInput = new Administrator("test@test.ru", "test_password", Utils.getCurrentTime(), false);
	}

	@Test
	public void testGetAdministrator() {
		Administrator administratorOutput = null;
		try {
			idAdministratorOutput = AdministratorAPI.addPrimaryAdministrator(administratorInput.getEmail(),
					administratorInput.getPassword());

			administratorOutput = AdministratorAPI.getAdministrator(idAdministratorOutput);

		} catch (AdministratorManyAccounts e) {
			e.printStackTrace();
		} catch (AdministratorNotExist e) {
			e.printStackTrace();
		} catch (NotCorrectInputData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(administratorInput.equals(administratorOutput));
	}

	@After
	public void removeTestAdministrator() {
		try {
			AdministratorAPI.removeAdministrator(idAdministratorOutput);
		} catch (NotCorrectInputData e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
