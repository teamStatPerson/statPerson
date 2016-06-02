import entity.Pages;
import org.hibernate.Session;

import java.util.Calendar;


/**
 * Created by alexey_n on 27.05.2016.
 */
public class Main {
    public static void main(final String[] args) throws Exception {

        Session session = HibernateUtil.getSession();

        String s = Downloader.downloadHTML("http://lenta.ru");
        Pages p = new Pages();
        p.setSiteId(1);
        p.setUrl("http://lenta.ru");
        p.setFoundDateTime(Calendar.getInstance());
        p.setHtml(s);

        RepositorySP<Pages> sp = new RepositorySP<Pages>();
        sp.addEntity(p);


    }
}
