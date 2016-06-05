package statPerson.element.administrator_price;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import exceptions.NotCorrectInputData;
import statPerson.Utils;
import statPerson.element.account.Account;
import statPerson.element.account.AccountDao;
import statPerson.element.price.Price;
import statPerson.element.price.PriceDao;

public class TestDao {

	private Integer id;

	@Test
	public void testDAO() {
		Account administratorInput = new Account("test2@test.ru", "test_password", Utils.getCurrentTime(),
				Account.PRIMARY_ADMINISTRATOR_ACCOUNT, Account.DEFAULF_EMPTY_LINKED_ADMINISRATOR);
		try {
			id = AccountDao.addPrimaryAdministrator(administratorInput.getEmail(), administratorInput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(id);

		Price price = new Price("test", 1, 2, 3);

		Integer idPrice = PriceDao.addPrice(price.getName(), price.getMaxAmountUsers(), price.getMaxAmountSites(),
				price.getDurationOfPriceDay());

		AdministratorPriceDao.addPriceToAdministrator(id, idPrice);

		List<AdministratorPrice> list = AdministratorPriceDao.getAllPricesAdministrator(id);

		Assert.assertEquals((int) list.get(0).getIdPrice(), (int) idPrice);
		Assert.assertEquals((int) list.get(0).getIdAdministrator(), (int) id);

		PriceDao.removePrice(idPrice);
		AccountDao.removeAccount(id);
	}
}
