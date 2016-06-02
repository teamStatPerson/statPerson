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
  private List<String> listUrlXMLgz; //файлы архивов gz, например https://lenta.ru/news/sitemap3.xml.g
  private String siteName;
    public ParsingXML(String siteName, String urlXML) {
        this.urlXML = urlXML;
        this.listUrl = new ArrayList<String>();
        this.listUrlXMLgz = new ArrayList<String>();
        this.siteName = siteName;
        doParseXML();
    }

  public void doParseXML() {
        //parseXML(urlXML + "/sitemap.xml");
      parseXML(urlXML);
        if (!(listUrlXMLgz.isEmpty())) {
            parseXMLgz(listUrlXMLgz);
        }
        if (listUrl.isEmpty()&(listUrlXMLgz.isEmpty())){ // если sitemap пустой, то возвращаем главную страницу
            listUrl.add(siteName);
        }
    }

    private void parseXMLgz(List<String> listUrlXMLgz) {
        for (String urlXMLgz: listUrlXMLgz) {
            String urlXMLtemp = urlXMLgz.substring(0, urlXMLgz.length()-3); // приведение gz архива к виду url
            parseXML(urlXMLtemp);
        }
    }

    public void printUrlTotal() {
        for (String url : listUrl) {
            System.out.println("url = " + url);
        }
    }

    private void parseXML(String urlXML) {
        DownloaderXML downloaderXML = new DownloaderXML(urlXML);
        Document doc = downloaderXML.getDoc();
        if (doc != null) {
            Elements links = doc.getElementsByTag("loc");
            if (links != null){
              for (Element link : links) {
                  String url = link.text();
                  addUrl(url);
                }
            }
        }
    }

   private void addUrl(String url) {
    boolean gzXML = url.matches("^http(.*).[xml.gz]$"); // проверка есть ли файл с gz архивом
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
