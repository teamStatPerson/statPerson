package crauler.job_manager;

import crauler.FakeCraulerJob;
import crauler.api.CraulerJob;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

public class ClaulerJobManager {
	private static ClaulerJobManager instance;
	private static CraulerJob job = new FakeCraulerJob();

	ClaulerJobManager getInstance() {
		if (instance == null) {
			instance = new ClaulerJobManager();
		}
		return instance;
	}
	
	public void addJob(Site site, Person person){
		job.doJob(site,person);
	}
}
