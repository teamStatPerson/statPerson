package statPerson.element.price;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDao {
	
	private Price price;
	private Integer idPrice;

	@Before
	public void before() {
		price = new Price("test",1,2,3);
	}

	@Test
	public void testDAO() {
		idPrice = PriceDao.addPrice(
				price.getName(), 
				price.getMaxAmountUsers(), 
				price.getMaxAmountSites(),
				price.getDurationOfPriceDay()
				);
		Price out = PriceDao.getPrice(idPrice);
		assertEquals(out,price);
	}
	

	@After
	public void after() {
		PriceDao.removePrice(idPrice);
	}
	
}
