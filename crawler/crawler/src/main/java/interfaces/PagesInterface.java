package interfaces;

import entity.Pages;
import org.jsoup.Connection;

import java.util.List;

/**
 * Created by alexey_n on 02.06.2016.
 */
public interface PagesInterface extends BaseInterface<Pages>{
    public Pages getPageByID(int idPage);

    public List<Pages> getFreshPagesint (int idSite, int countPages);
}
