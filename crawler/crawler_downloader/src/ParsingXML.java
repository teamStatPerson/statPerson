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
  private List<String> listUrlXMLqz; //файлы архивов qz, например https://lenta.ru/news/sitemap3.xml.gz

  public ParsingXML(String _urlXML) {
      urlXML = _urlXML;
      doParseXML();

  }

    private void doParseXML() {
        listUrl = new ArrayList<String>();
        listUrlXMLqz = new ArrayList<String>();
        parseXML(urlXML);
        if (listUrlXMLqz !=null) {
            parseXMLqz(listUrlXMLqz);
        }
        else System.out.println("пустой");
    }

    private void parseXMLqz(List<String> listUrlXMLqz) {
        for (String urlXMLqz: listUrlXMLqz
                ) {
            String urlXMLtemp = urlXMLqz.substring(0, urlXMLqz.length()-3); //
            parseXML(urlXMLtemp);
        }
    }

    public void printUrlTotal() {
        for (String url : listUrl
                ) {
            System.out.println("url = " + url);
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
