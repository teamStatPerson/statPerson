package crauler.java;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

/**
 * Created by Андрей on 01.06.2016.
 */
public class ParsingRobots {
	static Logger log = Logger.getLogger(ParsingRobots.class.getName());
	
	private String urlSite;
	private String urlSiteMap;
	private Boolean isRobotsFileFound;
	private Boolean isURLsiteMapFound;

	public ParsingRobots(String urlSite) {
		log.debug("In ParsingRobots()");
		log.debug("urlSite = "+urlSite);
		
		this.urlSite = urlSite;
		this.isRobotsFileFound = false;
		this.isURLsiteMapFound = false;
		this.urlSiteMap = "";
	}

	public String getUrlSiteMap() {
		log.debug("In ParsingRobots.getUrlSiteMap()");
		return urlSiteMap;
	}

	public Boolean getRobotsFileFound() {
		log.debug("In ParsingRobots.getRobotsFileFound()");
		return isRobotsFileFound;
	}

	public Boolean getURLsiteMapFound() {
		log.debug("In ParsingRobots.getURLsiteMapFound()");
		return isURLsiteMapFound;
	}

	public void foundURLSiteMap() {
		log.debug("In ParsingRobots.foundURLSiteMap()");
		System.out.println("urlsite " + urlSite);
		
		Document doc = DownloaderXML.getDoc(urlSite + "/robots.txt");

		if (doc != null) {

			String pageHTML = doc.text();
			int sitemap = pageHTML.indexOf("Sitemap:");
			int xml = pageHTML.indexOf("xml");
			String urlSiteMapTemp = "";
			if (((xml != -1)) & (sitemap != -1)) {
				urlSiteMapTemp = pageHTML.substring(pageHTML.indexOf("Sitemap") + 9, pageHTML.indexOf("xml") + 3);
			}
			isURLsiteMapFound = urlSiteMapTemp.matches("^http(.*).[xml.gz]$"); // проверка
																				// является
																				// ли
																				// ссылка
																				// сайтом
			if (isURLsiteMapFound) {
				urlSiteMap = urlSiteMapTemp;
				System.out.println("Файл Sitemap найден " + urlSiteMap);
			} else
				System.out.println("Файл Sitemap не найден");

		} else {
			isRobotsFileFound = false;
			System.out.println("Файл robots.txt не найден");
		}

		/*
		 * Pattern p = Pattern.compile("Sitemap:(.*).[xml]",
		 * Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE); Matcher m =
		 * p.matcher(pageHTML); String res = ""; System.out.println("START"); if
		 * (m.find()) { System.out.println("m.start() = " + m.start());
		 * System.out.println("m.end() = " + m.end());
		 * System.out.print(pageHTML.substring(m.start(), m.end()) + "*"); }
		 */

	}

}
