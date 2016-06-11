package crauler.api.interfaces;

import statPerson.element.person.Person;
import statPerson.element.site.Site;

public interface CraulerJob {
	// Crauler add info in table Pages
	// Crauler add info in table PersonPageRank
	// Crauler take Keywords from db
	void doJob(Site site, Person person);
}
