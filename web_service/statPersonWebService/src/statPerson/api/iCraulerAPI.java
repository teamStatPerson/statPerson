package statPerson.api;

import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

import java.util.List;

import statPerson.element.keyword.Keyword;

public interface iCraulerAPI {
	void addPage(Site site, Page page);

	void addRank(Person person, Page page, int rank);

	List<Keyword> getKeywords(Person person);
}
