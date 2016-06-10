package crauler.work;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import statPerson.element.page.Page;
import statPerson.element.page.PageDao;
import statPerson.element.site.Site;
import statPerson.element.site.SiteDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexey_n on 10.06.2016.
 */
public class WorkWithSitemap {
    private int idSite;
    private String urlRootXML;
    private List<String> listXML;
    private List<Page> listPages;
    private final static int COUNT_OF_PART_LIST_URL = 1000;
    private int firstIndex = 0;

    public WorkWithSitemap(int idSite) {
        this.idSite = idSite;
        listXML = new ArrayList<String>();
        listPages = new ArrayList<Page>();
        firstIndex = 0;
    }

    public void start() {
        initUrlRootXML(); //todo договориться о формате хранения адресов страниц
        parseXML(urlRootXML);
        int size = listXML.size();
        while (size > 0) {
            int lastIndex = size - 1;
            String urlXML = listXML.remove(lastIndex);
            size = listXML.size();
            parseXML(urlXML);
        }
        System.out.println("listPage" + listPages.size());
        recordPages();
    }

    private void parseXML(String urlRootXML) {
        Document doc = Downloader.downloadDocument(urlRootXML);
        if (doc != null) {
            addXML(doc);
            addPages(doc);
        }
    }

    private void addXML(Document doc) {
        Elements links = doc.select("sitemap loc");
        for (Element link : links) {
            String url = link.text();
//                System.out.println(url);
            Pattern pattern = Pattern.compile("^http(.*).xml");
            Matcher matcher = pattern.matcher(url);
            matcher.find();
            String xml = matcher.group();
            listXML.add(xml);
        }
    }

    private void addPages(Document doc) {
        Elements links = doc.select("url");
        for (Element link : links) {
            Page page = new Page();
            page.setSiteId(idSite);
            Elements loc = link.select("loc");
            for (Element element : loc
                    ) {
//                System.out.println(eli.text());
                page.setUrl(element.text());
            }
            Elements lastmod = link.select("lastmod");
            for (Element element : lastmod
                    ) {
//                System.out.println(element.text());
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") {
                    public Date parse(String source, ParsePosition pos) {
                        return super.parse(source.replaceFirst(":(?=[0-9]{2}$)", ""), pos);
                    }
                };
                try {
                    Date date = df.parse(element.text());
                    page.setFoundDateTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                listPages.add(page);
            }
        }
    }

    private void recordPages() {
        while (firstIndex < listPages.size()) {
            List<Page> nextPart;
            if (firstIndex + COUNT_OF_PART_LIST_URL < listPages.size()) {
                nextPart = listPages.subList(firstIndex, firstIndex + COUNT_OF_PART_LIST_URL);
                firstIndex += COUNT_OF_PART_LIST_URL;
                PageDao.addPage(nextPart);
            } else {
                nextPart = listPages.subList(firstIndex, listPages.size());
                firstIndex += COUNT_OF_PART_LIST_URL;
                PageDao.addPage(nextPart);
            }
        }
    }

    private void initUrlRootXML() {
        Site site = SiteDao.getSite(idSite);
        String urlSite = site.getName();
        urlRootXML = urlSite + "/sitemap.xml";
        System.out.println(urlRootXML);
    }
}
