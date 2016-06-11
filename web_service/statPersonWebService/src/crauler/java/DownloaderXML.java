package crauler.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Андрей on 29.05.2016.
 */
public class DownloaderXML {
	private Document doc;
	private final static int TIMEOUT = 3000;
	private final static String USER_AGENT = "Googlebot-News";
	private boolean flag;

	public DownloaderXML(String url) {
		flag = false;
		doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Проблемы с загрузкой Sitemap - " + url);
			flag = false;
		}
	}

	public Document getDoc() {

		return doc;
	}
}
