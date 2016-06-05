package statPerson.crauler.api;

import statPerson.crauler.elements.main.java.entity.Persons;
import statPerson.crauler.elements.main.java.entity.Sites;

public interface CraulerJob {
	// crauler add info in table Pages
	// crauler add info in table PersonPageRank
	// crauler take Keywords from db
	void doJob(Sites site, Persons person);
}