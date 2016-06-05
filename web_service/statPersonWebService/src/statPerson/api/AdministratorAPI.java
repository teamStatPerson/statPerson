package statPerson.api;

import statPerson.element.account.Account;
import statPerson.element.keyword.Keyword;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.elements.Sites;

// interface for administrator - Version 1.0

interface AdministratorAPI{
	// return null, if Administrator is not exist
	Account getAdministrator(String email, String password);
	
	// Persons
	// return null, if haven`t persons
	Person[] getPersons(Account administrator);
	void addPerson(Account administrator, Person person);
	void removePerson(Account administrator, Person person);
	
	// Sites
	// return null, if haven`t sites
	Sites[] getSites(Account administrator);
	void addSite(Account administrator, Sites site);
	void removeSite(Account administrator, Sites site);
	
	//Pages for krauler statistic
	// return null, if haven`t pages
	Page[] getPages(Sites site);
	
	// Keywords
	// return null, if haven`t keywords
	Keyword[] getKeywords(Account administrator, Person person);
	void addKeyword(Account administrator, Keyword keyword);
	void removeKeyword(Account administrator, Keyword keyword);
}
