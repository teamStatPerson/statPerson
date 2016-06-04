package statPerson.element.administrator_price;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import exceptions.NotCorrectInputData;
import statPerson.Utils;
import statPerson.element.administrator.Administrator;
import statPerson.element.administrator.AdministratorDao;
import statPerson.element.price.Price;
import statPerson.element.price.PriceDao;

public class TestDao {

	private Administrator administratorOutput;
	
	@Test
	public void testDAO() {
		Administrator administratorInput = new Administrator("test2@test.ru", "test_password", Utils.getCurrentTime(), false);
		try {
			administratorOutput = AdministratorDao.addPrimaryAdministrator(administratorInput.getEmail(),
					administratorInput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(administratorOutput);
		
		Price price = new Price("test",1,2,3);

		Integer idPrice = PriceDao.addPrice(
				price.getName(), 
				price.getMaxAmountUsers(), 
				price.getMaxAmountSites(),
				price.getDurationOfPriceDay()
				);
		
		AdministratorPriceDao.addPriceToAdministrator(administratorOutput.getId(), idPrice);
		
		
		List<AdministratorPrice> list = AdministratorPriceDao.getAllPricesAdministrator(administratorOutput.getId());
		
		Assert.assertEquals((int)list.get(0).getIdPrice(),(int)idPrice);
		Assert.assertEquals((int)list.get(0).getIdAdministrator(),(int)administratorOutput.getId());
		
		PriceDao.removePrice(idPrice);
		try {
			AdministratorDao.removeAdministrator(administratorOutput.getEmail(), administratorOutput.getPassword());
		} catch (NotCorrectInputData e) {
			e.printStackTrace();
		}
	}
}
