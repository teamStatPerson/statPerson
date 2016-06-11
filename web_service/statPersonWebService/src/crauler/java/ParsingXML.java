package crauler.java;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Андрей on 29.05.2016.
 */
public class ParsingXML {

	private String urlXML;
	private List<String> listUrl;
	private List<String> listUrlXMLgz; // файлы архивов gz, например
										// https://lenta.ru/news/sitemap3.xml.g
	private String siteName;
	private Integer countUrl; // счетчик сайтов обхода
	private Integer countUrlTotal; // максимальное количество сайтов обхода

	public ParsingXML(String siteName, String urlXML) {
		this.urlXML = urlXML;
		this.listUrl = new ArrayList<String>();
		this.listUrlXMLgz = new ArrayList<String>();
		this.siteName = siteName;
		countUrlTotal = 50;
		countUrl = 0;
		doParseXML();
	}

	public void doParseXML() {
		// parseXML(urlXML + "/sitemap.xml");
		parseXML(urlXML);
		if (!(listUrlXMLgz.isEmpty())) {
			parseXMLgz(listUrlXMLgz);
		}
		if (listUrl.isEmpty() & (listUrlXMLgz.isEmpty())) { // если sitemap
															// пустой, то
															// возвращаем
															// главную страницу
			// System.out.println("URL пустой");
			// System.out.println("siteName " + siteName);
			listUrl.add(siteName);
		}
	}

	private void parseXMLgz(List<String> listUrlXMLgz) {
		for (String urlXMLgz : listUrlXMLgz) {
			String urlXMLtemp = urlXMLgz.substring(0, urlXMLgz.length() - 3); // приведение
																				// gz
																				// архива
																				// к
																				// виду
																				// url
			parseXML(urlXMLtemp);
		}
	}

	public void printUrlTotal() {
		for (String url : listUrl) {
			System.out.println("url = " + url);
		}
	}

	private void parseXML(String urlXML) {
		// System.out.println("urlXML = " + urlXML);
		DownloaderXML downloaderXML = new DownloaderXML(urlXML);
		Document doc = downloaderXML.getDoc();
		if (doc != null) {
			// System.out.println("doc not null" );
			Elements links = doc.getElementsByTag("loc");
			if (links != null) {
				for (Element link : links) {
					String url = link.text();
					addUrl(url);
				}
			}
		}
		// System.out.println("doc null" );
	}

	private void addUrl(String url) {
		boolean gzXML = url.matches("^http(.*).[xml.gz]$"); // проверка есть ли
															// файл с gz архивом
		if ((gzXML) & (countUrl <= countUrlTotal)) {
			listUrlXMLgz.add(url);
			// System.out.println("есть " + url);
			countUrl++;
		} else {
			if (countUrl <= countUrlTotal) {
				// System.out.println("url = " + url);
				// System.out.println("countUrl = " + countUrl);
				listUrl.add(url);
				countUrl++;
			}
		}
	}

	public List<String> getListUrl() {
		return listUrl;
	}

}
