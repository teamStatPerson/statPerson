package statPerson.api;

import statPerson.element.account.Account;
import statPerson.element.keyword.Keyword;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

// interface for administrator - Version 1.0

interface iAdministratorAPI{
	// return null, if Administrator is not exist
	Account getAdministrator(String email, String password);
	
	// Persons
	// return null, if haven`t persons
	Person[] getPersons(Account administrator);
	void addPerson(Account administrator, Person person);
	void removePerson(Account administrator, Person person);
	
	// Sites
	// return null, if haven`t sites
	Site[] getSites(Account administrator);
	void addSite(Account administrator, Site site);
	void removeSite(Account administrator, Site site);
	
	//Pages for krauler statistic
	// return null, if haven`t pages
	Page[] getPages(Site site);
	
	// Keywords
	// return null, if haven`t keywords
	Keyword[] getKeywords(Account administrator, Person person);
	void addKeyword(Account administrator, Keyword keyword);
	void removeKeyword(Account administrator, Keyword keyword);
}
