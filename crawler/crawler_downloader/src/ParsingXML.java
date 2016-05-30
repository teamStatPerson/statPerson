import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Андрей on 29.05.2016.
 */
public class ParsingXML {

  private List<String> listUrl;
  private List<String> listUrlXMLqz;

  public ParsingXML() {
      listUrl = new ArrayList<String>();
      listUrlXMLqz = new ArrayList<String>();
      parseXML("https://lenta.ru/sitemap.xml");
      for (String urlXMLqz: listUrlXMLqz
           ) {
        parseXML(urlXMLqz.substring(0, urlXMLqz.length()-3));
      }
  }

    public void parseXML(String urlXML) {
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
    boolean qzXML = url.matches("^http(.*).[xml.qz]$"); // проверка есть ли файл с qz архивом
        if (qzXML) {
            listUrlXMLqz.add(url);
        } else {
            listUrl.add(url);
        }
    }

    public List<String> getListUrl() {
        return listUrl;
    }

}
