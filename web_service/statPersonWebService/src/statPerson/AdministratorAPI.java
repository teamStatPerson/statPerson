package statPerson;
// interface for administrator - Version 1.0

interface iAdministratorAPI{
	// return null, if Administrator is not exist
	Administrator getAdministrator(String email, String password);
	
	// Persons
	// return null, if haven`t persons
	Person[] getPersons(Administrator administrator);
	void addPerson(Administrator administrator, Person person);
	void removePerson(Administrator administrator, Person person);
	
	// Sites
	// return null, if haven`t sites
	Site[] getSites(Administrator administrator);
	void addSite(Administrator administrator, Site site);
	void removeSite(Administrator administrator, Site site);
	
	//Pages for crauler statistic
	// return null, if haven`t pages
	Page[] getPages(Site site);
	
	// Keywords
	// return null, if haven`t keywords
	Keyword[] getKeywords(Administrator administrator, Person person);
	void addKeyword(Administrator administrator, Keyword keyword);
	void removeKeyword(Administrator administrator, Keyword keyword);
}

interface Person{
	String getPersonName();
}

interface Page{
	String getURL();
	
	// return -1, if Page never scan
	long getFoundDateTime();
	// return -1, if Page never scan
	long getLastScanTime();
}