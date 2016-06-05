package statPerson.demo;

import exceptions.NotCorrectInputData;
import statPerson.Utils;
import statPerson.element.account.Account;
import statPerson.element.account.AccountDao;
import statPerson.element.administrator_price.AdministratorPriceDao;
import statPerson.element.price.PriceDao;

public class Demonstration {
	public static void main(String[] args) {
		System.out.println("Create demonstratrion account");
		Account administrator = new Account("demo", "demo", Utils.getCurrentTime(),
				Account.PRIMARY_ADMINISTRATOR_ACCOUNT, Account.DEFAULF_EMPTY_LINKED_ADMINISRATOR);
		Integer id = null;
		try {
			id = AccountDao.addPrimaryAdministrator(administrator.getEmail(), administrator.getEmail());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		} finally {
			Integer idPrice = PriceDao.addPrice("demo", 3, 5, 10);
			AdministratorPriceDao.addPriceToPrimaryAdministrator(id, idPrice);
		}
	}
}
