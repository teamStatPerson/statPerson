package crauler.work;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by alexey_n on 02.06.2016.
 */
public class Downloader {
    private final static int TIMEOUT = 9000;
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < html.length(); i++) {
            char ch = html.charAt(i);
            if (!Character.isHighSurrogate(ch) && !Character.isLowSurrogate(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static Document downloadDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Проблемы с загрузкой - " + url);
        }
        return doc;
    }

    public static Date getLastModified(String url) {
        Date lastModified = null;
        try {
            Connection.Response rs = Jsoup.connect(url).ignoreContentType(true).execute();
            String dateString = rs.headers().get("Last-Modified");
            System.out.println(dateString);
            SimpleDateFormat format = new SimpleDateFormat("EE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
            lastModified = format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastModified;
    }
}
