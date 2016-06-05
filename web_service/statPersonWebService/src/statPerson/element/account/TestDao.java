package statPerson.element.account;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.NotCorrectInputData;
import statPerson.Utils;

public class TestDao {

	private Account administratorInput;
	private Integer id;

	@Before
	public void before() {
		administratorInput = new Account("test@test.ru", "test_password", Utils.getCurrentTime(),
				Account.PRIMARY_ADMINISTRATOR_ACCOUNT, Account.DEFAULF_EMPTY_LINKED_ADMINISRATOR);
	}

	@Test
	public void testDAO() {
		try {
			id = AccountDao.addPrimaryAdministrator(administratorInput.getEmail(), administratorInput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}

		Account out = AccountDao.getAccount(id);

		Assert.assertTrue(administratorInput.getEmail().equals(out.getEmail()));
		Assert.assertTrue(administratorInput.getPassword().equals(out.getPassword()));
	}

	@After
	public void after() {
		AccountDao.removeAccount(id);
	}

}
