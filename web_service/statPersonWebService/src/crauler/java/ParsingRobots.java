package crauler.java;

import org.jsoup.nodes.Document;

/**
 * Created by Андрей on 01.06.2016.
 */
public class ParsingRobots {
	private String urlSite;
	private String urlSiteMap;
	private Boolean isRobotsFileFound;
	private Boolean isURLsiteMapFound;

	public ParsingRobots(String urlSite) {
		this.urlSite = urlSite;
		this.isRobotsFileFound = false;
		this.isURLsiteMapFound = false;
		this.urlSiteMap = "";
	}

	public String getUrlSiteMap() {
		return urlSiteMap;
	}

	public Boolean getRobotsFileFound() {
		return isRobotsFileFound;
	}

	public Boolean getURLsiteMapFound() {
		return isURLsiteMapFound;
	}

	public void foundURLSiteMap() {
		System.out.println("urlsite " + urlSite);
		DownloaderXML downloaderXML = new DownloaderXML(urlSite + "/robots.txt");
		Document doc = downloaderXML.getDoc();

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
