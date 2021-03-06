package statPerson.api;

import statPerson.crauler.elements.main.java.entity.Keywords;
import statPerson.crauler.elements.main.java.entity.Pages;
import statPerson.crauler.elements.main.java.entity.Persons;
import statPerson.crauler.elements.main.java.entity.Sites;
import statPerson.element.account.Account;

// interface for administrator - Version 1.0

interface AdministratorAPI {
	// return null, if Administrator is not exist
	Account getAdministrator(String email, String password);

	// Persons
	// return null, if haven`t persons
	Persons[] getPersons(Account administrator);

	void addPerson(Account administrator, Persons person);

	void removePerson(Account administrator, Persons person);

	// Sites
	// return null, if haven`t sites
	Sites[] getSites(Account administrator);

	void addSite(Account administrator, Sites site);

	void removeSite(Account administrator, Sites site);

	// Pages for krauler statistic
	// return null, if haven`t pages
	Pages[] getPages(Sites site);

	// Keywords
	// return null, if haven`t keywords
	Keywords[] getKeywords(Account administrator, Persons person);

	void addKeyword(Account administrator, Keywords keyword);

	void removeKeyword(Account administrator, Keywords keyword);
}
