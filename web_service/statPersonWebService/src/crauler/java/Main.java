package crauler.java;

/**
 * Created by Андрей on 22.05.2016.
 */

import java.util.Calendar;

import org.apache.log4j.Logger;

import statPerson.element.person.Person;
import statPerson.element.site.Site;

public class Main {
	static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		log.debug("In main(String[] args)");
		Site site = new Site("https://lenta.ru", Calendar.getInstance());
		Person testPerson = new Person(5000,"тест персон");
		Crawler crawler = new Crawler();
		crawler.doJob(site, testPerson);
	}
}