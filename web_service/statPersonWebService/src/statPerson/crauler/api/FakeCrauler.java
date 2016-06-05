package statPerson.crauler.api;

import elements.Persons;
import elements.Sites;

public class FakeCrauler implements CraulerJob {

	@Override
	public void doJob(Sites site, Persons person) {
		System.out.println("Crauler started job in site" + site.getName() + " and find person: " + person.getName());
	}

}
