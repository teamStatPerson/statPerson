// interface for administrator - Version 1.0

interface AdministratorAPI{
	Administrator getAdministrator(String email, String password);
	
	// Persons
	Person[] getPersons(Administrator administrator);
	void addPerson(Administrator administrator, Person person);
	void removePerson(Administrator administrator, Person person);
	
	// Sites
	Site[] getSites(Administrator administrator);
	void addSite(Administrator administrator, Site site);
	void removeSite(Administrator administrator, Site site);
	
	// Keywords
	Keyword[] getKeywords(Administrator administrator, Person person);
	void addKeyword(Administrator administrator, Keyword keyword);
	void removeKeyword(Administrator administrator, Keyword keyword);
}

interface User{
	String getEmail();
}

interface Administrator{
	String getEmail();
}

interface Site{
	String getSiteUrl();
}

interface Keyword{
	String getKeywordName();
}

interface Person{
	String getPersonName();
}
