package crauler.api.interfaces;

import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

import java.util.List;

public interface iCraulerApiDatabase {
	Page addPage(Site site, String page, String html);

	void addRank(Person person, Integer pageId, String keyword, int rank);

	List<String> getKeywords(Person person);
}
