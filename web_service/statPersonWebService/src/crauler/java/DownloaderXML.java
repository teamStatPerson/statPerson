package crauler.java;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Андрей on 29.05.2016.
 */
public class DownloaderXML {
	static Logger log = Logger.getLogger(DownloaderXML.class.getName());

	private final static int TIMEOUT = 3000;
	private final static String USER_AGENT = "Googlebot-News";

	public static Document getDoc(String url) {
		// log.debug("In DownloaderXML()");
		// log.debug("url = " + url);

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get();
		} catch (IOException e) {
			log.debug("In DownloaderXML() -- Cannot download --" + url);
		}
		return doc;
	}
}
