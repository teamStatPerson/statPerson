package statPerson.element.administrator_site;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import exceptions.NotCorrectInputData;
import statPerson.Utils;
import statPerson.element.account.Account;
import statPerson.element.account.AccountDao;

public class TestDao {

	@Test
	public void test() {
		

		Account administratorInput = new Account("test_site@test.ru", "test_password", Utils.getCurrentTime(),
				Account.PRIMARY_ADMINISTRATOR_ACCOUNT, Account.DEFAULF_EMPTY_LINKED_ADMINISRATOR);
		Integer idAdmin = null;
		try {
			idAdmin = AccountDao.addPrimaryAdministrator(administratorInput.getEmail(), administratorInput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
		assertNotNull(idAdmin);
		
		AdministratorSite in = new AdministratorSite(idAdmin, 100);
		Integer id = AdministratorSiteDao.addAdministratorSite(in.getIdAdministrator(), in.getIdSite());
		assertNotNull(id);
		AdministratorSite out = AdministratorSiteDao.getAdministratorSiteById(id);
		assertEquals(in.getIdAdministrator(), out.getIdAdministrator());
		AdministratorSiteDao.removeSiteFromAdministrator(out.getIdAdministrator(), out.getIdSite());
		

		AccountDao.removeAccount(idAdmin);
	}
}
