package crauler.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Андрей on 29.05.2016.
 */
public class ParsingXML {
	static Logger log = Logger.getLogger(ParsingXML.class.getName());

	private String urlXML;
	private List<String> listUrl;
	private List<String> listUrlXMLgz; // файлы архивов gz, например
										// https://lenta.ru/news/sitemap3.xml.g
	private String siteName;
	
	// счетчик сайтов обхода
	private int countUrl; 
	
	// максимальное количество сайтов обхода
	private static final Integer MAXIMAL_AMOUNT_URL = 150; 

	public ParsingXML(String siteName, String urlXML) {
		log.debug("In ParsingRobots()");
		log.debug("siteName = " + siteName);
		log.debug("urlXML = " + urlXML);

		this.urlXML = urlXML;
		this.listUrl = new ArrayList<String>();
		this.listUrlXMLgz = new ArrayList<String>();
		this.siteName = siteName;
		countUrl = 0;
		doParseXML();
	}

	public void doParseXML() {
		log.debug("In ParsingXML.doParseXML()");

		parseXML(urlXML);
		if (!(listUrlXMLgz.isEmpty())) {
			parseXMLgz(listUrlXMLgz);
		}
		// если sitemap пустой, то возвращаем главную страницу
		if (listUrl.isEmpty() & (listUrlXMLgz.isEmpty())) {
			listUrl.add(siteName);
		}
	}

	private void parseXMLgz(List<String> listUrlXMLgz) {
		log.debug("In ParsingXML.parseXMLgz()");
		log.debug("listUrlXMLgz ");
		for (String url : listUrlXMLgz)
			log.debug(url);

		for (String urlXMLgz : listUrlXMLgz) {
			// приведение gz архива к виду url
			String urlXMLtemp = urlXMLgz.substring(0, urlXMLgz.length() - 3);
			parseXML(urlXMLtemp);
			if (countUrl > MAXIMAL_AMOUNT_URL) {
				return;
			}
		}
	}

	private void parseXML(String urlXML) {
		log.debug("In ParsingXML.parseXML()");
		log.debug("urlXML = " + urlXML);

		Document doc = DownloaderXML.getDoc(urlXML);
		if (doc != null) {
			Elements links = doc.getElementsByTag("loc");
			if (links != null) {
				for (Element link : links) {
					String url = link.text();
					addUrl(url);
				}
			}
		}
	}

	private void addUrl(String url) {
		// проверка есть ли файл с gz архивом
		boolean gzXML = url.matches("^http(.*).[xml.gz]$");
		if (countUrl > MAXIMAL_AMOUNT_URL) {
			return;
		}
		countUrl++;
		if (gzXML) {
			listUrlXMLgz.add(url);
		} else {
			listUrl.add(url);
		}
	}

	public List<String> getListUrl() {
		return listUrl;
	}

}
