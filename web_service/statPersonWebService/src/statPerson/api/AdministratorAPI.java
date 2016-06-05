package statPerson.api;

import statPerson.element.account.Account;
import statPerson.element.keyword.Keyword;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

public class AdministratorAPI implements iAdministratorAPI {
dfghjkl
	@Override
	public Account getAdministrator(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person[] getPersons(Account administrator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPerson(Account administrator, Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePerson(Account administrator, Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public Site[] getSites(Account administrator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSite(Account administrator, Site site) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSite(Account administrator, Site site) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page[] getPages(Site site) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Keyword[] getKeywords(Account administrator, Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addKeyword(Account administrator, Keyword keyword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeKeyword(Account administrator, Keyword keyword) {
		// TODO Auto-generated method stub

	}

}
