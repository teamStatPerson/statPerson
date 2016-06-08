package crauler;

import crauler.api.CraulerJob;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

public class FakeCraulerJob implements CraulerJob{

	@Override
	public void doJob(Site site, Person person) {
		System.out.println("Crauler start {site,person}={"+site.getName()+" , "+person.getName()+"}");
	}

}
