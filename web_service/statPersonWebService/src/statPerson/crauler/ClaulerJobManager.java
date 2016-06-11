package statPerson.crauler;

import crauler.api.interfaces.CraulerJob;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

public class ClaulerJobManager {
	private CraulerJob job;

	public ClaulerJobManager(CraulerJob job){
		this.job = job;
	}
	
	public void addJob(Site site, Person person){
		job.doJob(site,person);
	}
}
