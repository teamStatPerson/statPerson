import entity.Pages;
import implementation.PagesImpl;
import interfaces.PagesInterface;

/**
 * Created by alexey_n on 06.06.2016.
 */
public class HTMLPage {

    public static void recordHTML(int idPage) {
        PagesInterface pagesInterface = new PagesImpl();
        Pages page = new Pages();
        page = pagesInterface.getPageByID(idPage);
        String url = page.getUrl();
        String html = Downloader.downloadHTML(url);
        page.setHtml(html);
        pagesInterface.saveOrUpdateEntity(page);
    }

    public static String readHTML(int idPage) {
        PagesInterface pagesInterface = new PagesImpl();
        Pages page = new Pages();
        page = pagesInterface.getPageByID(idPage);
        String html = page.getHtml();
        return html;
    }

}
