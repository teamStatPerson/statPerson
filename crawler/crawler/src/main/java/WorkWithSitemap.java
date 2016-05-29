import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexey_n on 24.05.2016.
 */
public class WorkWithSitemap {
    private List<String> listXML;
    private List<String> listUrl;
    private String urlRootXML;
    private final static int COUNT_OF_PART_LIST_URL = 1000;
    private int firstIndex; //todo узкое место, хотя и так пойдет, надеемся что случайно метод не дернут и значение не изменят

    public WorkWithSitemap(String url) {
        urlRootXML = url + "/sitemap.xml"; //todo добавить проверку принимаемой ссылки (привести к нужному виду)
        listXML = new ArrayList<String>();
        listUrl = new ArrayList<String>();
        firstIndex = 0;
    }

    public List<String> getListUrl() {
        parseXML(urlRootXML);
        int size = listXML.size();
        while (size > 0) {
            int lastIndex = size - 1;
            String urlXML = listXML.remove(lastIndex);
            size = listXML.size();
            parseXML(urlXML);
        }
        return listUrl;
    }

    private void parseXML(String urlXML) {
        Document doc = Downloader.downloadXML(urlXML); //todo добавить обработку когда не можем загрузить даже один xml и возвращаем listUrl без элементов
        if (doc != null) {
            Elements links = doc.getElementsByTag("loc");
            for (Element link : links) {
                String url = link.text();
                addUrlOrSitemapXML(url);
            }
        }
    }

    private void addUrlOrSitemapXML(String url) {
        boolean isXML = url.matches("(.*).gz$|(.*).xml$");
        if (isXML) {
            Pattern pattern = Pattern.compile("^http(.*).xml");
            Matcher matcher = pattern.matcher(url);
            matcher.find();
            String xml = matcher.group();
            listXML.add(xml);
            System.out.println("Добавили XML - " + xml);
        } else {
            listUrl.add(url);
            System.out.println("Добавили URL - " + url);
        }
    }

    public boolean isEmptyListUrl() {
        boolean isEmpty = listUrl.size() == 0;
        return isEmpty;
    }

    public List<String> getNextPartOfListUrl() {
        List<String> nextPart = new ArrayList<String>();
        if (firstIndex + COUNT_OF_PART_LIST_URL < listUrl.size()) {
            nextPart = listUrl.subList(firstIndex, firstIndex + COUNT_OF_PART_LIST_URL);
            firstIndex += COUNT_OF_PART_LIST_URL;
            return nextPart;
        } else {
            nextPart = listUrl.subList(firstIndex, listUrl.size());
            firstIndex += COUNT_OF_PART_LIST_URL;
            return nextPart;
        }
    }

    public boolean isNextPart() {
        boolean isNextPart = firstIndex < listUrl.size();
        return isNextPart;
    }
}
