package statPerson.api;

import java.util.List;

import statPerson.element.administrator_price.AdministratorPrice;
import statPerson.element.price.Price;

public interface PriceAPI {
	List<Price> getPrices();
	void addPriceToPrimaryAdministrator(Integer idAccount, Integer idPrice);
	List<AdministratorPrice> getAllPricesAdministrator(Integer idAdministrator);
}
