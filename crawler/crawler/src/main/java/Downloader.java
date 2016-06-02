import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by alexey_n on 02.06.2016.
 */
public class Downloader {
    private final static int TIMEOUT = 3000;
    private final static String USER_AGENT = "Googlebot-News";

    public static String downloadHTML(String url) {
        Document doc = null;
        String html = null;
        try {
            doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get();
            html = doc.html();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Проблемы с загрузкой Страницы - " + url);
        }
        return html;
    }

    public static Document downloadXML(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Проблемы с загрузкой Sitemap - " + url);
        }
        return doc;
    }
}
