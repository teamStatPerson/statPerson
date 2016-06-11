package crauler.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 05.06.2016.
 */
public class Crawler {
	private List<String> urlSites = new ArrayList<String>();

	public Crawler() {
	}

	public void doJob(String site, List<String> keywords) {
		ParsingRobots parsingRobots = new ParsingRobots(site);
		parsingRobots.foundURLSiteMap();
		// parsingRobots.getRobotsFileFound();
		if (parsingRobots.getURLsiteMapFound() == true) { // если ссылка на
															// sitemap найдена в
															// robots.txt
			// System.out.println("найдена");
			// System.out.println("URLSM = " + parsingRobots.getUrlSiteMap());
			doParsing(site, parsingRobots.getUrlSiteMap());
		} else {
			doParsing(site, site + "/sitemap.xml");
		}
		// System.out.println("fff");
		printUrl(urlSites);

		doStatPerson(keywords);
	}
	// raitingPutin - рейтинг персоны на каждой странице сайта
	// raitngTotal - рейтинг персоны на всем сайте

	private void doStatPerson(List<String> keywords) {
		Integer raitngTotal = 0;
		for (String url : urlSites) {
			ParsingHTML parsingHTML = new ParsingHTML(url, keywords);

			int raitingPutin = parsingHTML.raitingPerson();
			raitngTotal = raitngTotal + raitingPutin;
			// TODO: 05.06.2016 сюда вставить запись в таблицу PagePersonRank
			// рейтинг личности, url - имя сайта, raitingPutin - рейтинг на
			// странице url)
			// System.out.println("На странице " + url + " Рейтинг равен " +
			// raitingPutin);
		}
		System.out.println("Общий рейтинг " + raitngTotal);
	}

	private void doParsing(String siteName, String urlSiteMap) {
		// List<String> urlSites = new ArrayList<String>();
		ParsingXML parsingXML = new ParsingXML(siteName, urlSiteMap);
		urlSites = parsingXML.getListUrl();
		// TODO: 05.06.2016 сюда вставить запись в таблицу Pages сайта (значение
		// переменной urlSites)
	}

	public static void printUrl(List<String> listUrl) {
		/*
		 * for (String url : listUrl) { System.out.println("url= " + url); }
		 */
		System.out.println("Количество найденных url = " + listUrl.size());
	}

}
