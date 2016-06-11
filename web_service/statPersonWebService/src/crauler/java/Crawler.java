package crauler.java;

import java.util.List;

import org.apache.log4j.Logger;

import crauler.FakeCraulerApiDatabase;
import crauler.api.CraulerJob;
import crauler.api.interfaces.iCraulerAPI;
import statPerson.element.page.Page;
import statPerson.element.person.Person;
import statPerson.element.site.Site;

/**
 * Created by Андрей on 05.06.2016.
 */
public class Crawler implements CraulerJob {
	static Logger log = Logger.getLogger(Crawler.class.getName());

	private iCraulerAPI db = new FakeCraulerApiDatabase();

	@Override
	public void doJob(Site siteDB, Person personDB) {
		log.debug("In Crawler.doJob ");
		log.debug("site = " + siteDB);
		log.debug("person =  " + personDB.getName());

		List<String> pages = parsingSite(siteDB.getName());
		List<String> keywords = db.getKeywords(personDB);

		int[] ranks = new int[keywords.size()];
		for (String page : pages) {
			String html = DownloaderXML.getDoc(page).html();
			if (html.length() > 0) {
				ranks = ParsingHTML.rankPerson(html, keywords);
				Page pageDB = db.addPage(siteDB, page, html);
				for (int i = 0; i < ranks.length; i++) {
					if (ranks[i] > 0)
						db.addRank(personDB, pageDB.getId(), keywords.get(i), ranks[i]);
				}
			}
		}
	}

	private List<String> parsingSite(String site) {
		ParsingRobots parsingRobots = new ParsingRobots(site);
		ParsingXML parsingXML = null;
		if (parsingRobots.getURLsiteMapFound() == true) {
			parsingXML = new ParsingXML(site, parsingRobots.getUrlSiteMap());
		} else {
			parsingXML = new ParsingXML(site, site + "/sitemap.xml");
		}
		return parsingXML.getListUrl();
	}
}
