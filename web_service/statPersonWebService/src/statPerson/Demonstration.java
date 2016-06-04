package statPerson;

import exceptions.NotCorrectInputData;
import statPerson.element.administrator.Administrator;
import statPerson.element.administrator.AdministratorDao;
import statPerson.element.administrator_price.AdministratorPriceDao;
import statPerson.element.price.PriceDao;

public class Demonstration {
	public static void main(String[] args) {
		System.out.println("Create demonstratrion account");
		Administrator administrator = new Administrator("demo", "demo", Utils.getCurrentTime(), false);
		try {
			AdministratorDao.removeAdministrator(administrator.getEmail(), administrator.getEmail());
		} catch (NotCorrectInputData e1) {
			e1.printStackTrace();
		} finally {
			try {
				administrator = AdministratorDao.addPrimaryAdministrator(administrator.getEmail(),
						administrator.getEmail());
			} catch (NotCorrectInputData e) {
				e.printStackTrace();
			} finally {
				Integer idPrice = PriceDao.addPrice("demo", 3, 5, 10);
				AdministratorPriceDao.addPriceToAdministrator(administrator.getId(), idPrice);
			}
		}
	}
}
