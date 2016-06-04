package statPerson.api;

import statPerson.administrator.Administrator;
import statPerson.elements.Keywords;
import statPerson.elements.Pages;
import statPerson.elements.Persons;
import statPerson.elements.Sites;

// interface for administrator - Version 1.0

interface AdministratorAPI{
	// return null, if Administrator is not exist
	Administrator getAdministrator(String email, String password);
	
	// Persons
	// return null, if haven`t persons
	Persons[] getPersons(Administrator administrator);
	void addPerson(Administrator administrator, Persons person);
	void removePerson(Administrator administrator, Persons person);
	
	// Sites
	// return null, if haven`t sites
	Sites[] getSites(Administrator administrator);
	void addSite(Administrator administrator, Sites site);
	void removeSite(Administrator administrator, Sites site);
	
	//Pages for krauler statistic
	// return null, if haven`t pages
	Pages[] getPages(Sites site);
	
	// Keywords
	// return null, if haven`t keywords
	Keywords[] getKeywords(Administrator administrator, Persons person);
	void addKeyword(Administrator administrator, Keywords keyword);
	void removeKeyword(Administrator administrator, Keywords keyword);
}
