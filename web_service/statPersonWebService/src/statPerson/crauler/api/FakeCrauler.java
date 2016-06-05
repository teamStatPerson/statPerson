package statPerson.crauler.api;

import statPerson.crauler.elements.main.java.entity.Persons;
import statPerson.crauler.elements.main.java.entity.Sites;

public class FakeCrauler implements CraulerJob {

	@Override
	public void doJob(Sites site, Persons person) {
		System.out.println("Crauler started job in site" + site.getName() + " and find person: " + person.getName());
	}

}
