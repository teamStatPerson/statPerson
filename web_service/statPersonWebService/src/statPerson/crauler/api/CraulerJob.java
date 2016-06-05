package statPerson.crauler.api;

import elements.Persons;
import elements.Sites;

public interface CraulerJob {
	// crauler add info in table Pages
	// crauler add info in table PersonPageRank
	// crauler take Keywords from db
	void doJob(Sites site, Persons person);
}