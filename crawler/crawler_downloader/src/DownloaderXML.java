import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Андрей on 29.05.2016.
 */
public class DownloaderXML {
    private Document doc;
    private final static int TIMEOUT = 3000;
    private final static String USER_AGENT = "Googlebot-News";

    public DownloaderXML(String url) {
        doc = null;
        try {
          doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Проблемы с загрузкой Sitemap - " + url);
        }
    }

    public Document getDoc() {
        return doc;
    }
}
