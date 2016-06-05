package statPerson.api;

import java.util.List;

import statPerson.element.account.Account;
import statPerson.element.keyword.Keyword;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

public interface interfaceAPI{

	//ADMINISTRATOR
	// Only primary administrator can pay for price
	Integer addPrimaryAdministrator(String email, String password);
	
	//par.3.1.1. Primary administrator can create secondary administrator
	Integer addSecondaryAdministrator(String email, String password, int idLinkedAdministrator);
	
	//par.3.2.1. Administrator can create and delete user 
	Integer addUser(String email, String password, int idLinkedAdministrator);
	void removeAccount(Integer idAccount);
	
	//par.3.2.2.
	List<Person> getPersons(Account account);
	
	//par.3.3.2.
	void changePassword(Account account, String newPassword);
	
	
	
	
	
	// Administrator statistic
	// par.5.1.1
	int amountPagesFromSite(Site site);
	// par.5.1.2
	int amountPagesInDBbutNeverScan(Site site);
	// par.5.1.3
	int amountPagesInDBscanned(Site site);
	

	//Pages for krauler statistic
	// return null, if haven`t pages
	List<Page> getPages(Site site);
	
	
	
	// return null, if Administrator is not exist
	Account getAdministrator(String email, String password);

	Account getUser(String email, String password);
	
	// Persons
	// return null, if haven`t persons
	void addPerson(Account administrator, Person person);
	void removePerson(Account administrator, Person person);
	
	// Sites
	// return null, if haven`t sites
	List<Site> getSites(Account administrator);
	void addSite(Account administrator, Site site);
	void removeSite(Account administrator, Site site);
	
	
	// Keywords
	// return null, if haven`t keywords
	Keyword[] getKeywords(Account administrator, Person person);
	void addKeyword(Account administrator, Keyword keyword);
	void removeKeyword(Account administrator, Keyword keyword);
}
