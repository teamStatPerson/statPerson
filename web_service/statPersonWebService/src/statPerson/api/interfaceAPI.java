package statPerson.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import statPerson.element.account.Account;
import statPerson.element.administrator_price.AdministratorPrice;
import statPerson.element.keyword.Keyword;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.price.Price;
import statPerson.element.site.Site;

public interface interfaceAPI{

	/**********************************************/
	// Only primary administrator can pay for price
	Integer addPrimaryAdministrator(String email, String password);

	// par.3.1.1. Primary administrator can create secondary administrator
	Integer addSecondaryAdministrator(String email, String password, int idLinkedAdministrator);

	// par.3.2.1. Administrator can create and delete user 
	Integer addUser(String email, String password, int idLinkedAdministrator);
	void removeAccount(Integer idAccount);

	// par.3.2.2.
	List<Person> getPersons(Account account);
	List<Person> getPersons(String email, String password);

	// par.3.3.2.
	void changePassword(Account account, String newPassword);
	
	/**********************************************/
	// par.4.1.1
	// Sites
	List<Site> getSites(Account administrator);
	
	//return id of site
	Integer addSite(Account administrator, Site site);
	
	void removeSite(Account administrator, Site site);

	// par.4.1.2
	// Persons
	
	// return id of person
	Integer addPerson(Account administrator, Person person);
	
	void removePerson(Account administrator, Person person);

	/**********************************************/
	
	// par.4.1.3
	// Prices
	// return all allowable prices
	List<Price> getPrices();
	
	// Only Primary administrator can buy the price
	void addPriceToPrimaryAdministrator(Integer idAccount, Integer idPrice);
	
	// return all price of that administrator
	List<AdministratorPrice> getAllPricesAdministrator(Integer idAdministrator);

	/**********************************************/
	
	// ADMINISTRATOR STATISTIC
	// par.5.1.1
	int amountPagesFromSite(Site site);
	
	// par.5.1.2
	int amountPagesInDBbutNeverScan(Site site);
	
	// par.5.1.3
	int amountPagesInDBscanned(Site site);
	
	/**********************************************/
	
	// USER STATISTIC
	// par.6.1.1 ДТЗ
	// par.2.1.1 Функциональные требования
	Map<Date, Integer> getRankByPerDayByPerson(Person person, Date beginDate, Date endDate);
	
	// par.6.1.2
	Map<Person, Page> getStatisticPersonPage();
	
	// par.6.1.3
	Map<Person, Page> getStatisticPersonNewPages();
	
	// par.6.2.2
	Map<Site, Integer> getStatisticSiteAmountPage();
	
	// par.6.4
	// ignored
	
	/**********************************************/
	
	// par.7.1
	// ignored
	
	// par.7.2
	// ignored
	
	/**********************************************/
	
	

	//Pages for krauler statistic
	// return null, if haven`t pages
	//List<Page> getPages(Site site);
	// no need
	

	// Keywords
	// return null, if haven`t keywords
	Keyword[] getKeywords(Account administrator, Person person);
	void addKeyword(Account administrator, Keyword keyword);
	void removeKeyword(Account administrator, Keyword keyword);
}
