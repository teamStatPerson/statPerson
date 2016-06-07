package implementation;

import entity.Pages;
import interfaces.PagesInterface;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by alexey_n on 02.06.2016.
 */
public class PagesImpl extends BaseImpl<Pages> implements PagesInterface {
    @Override
    public Pages getPageByID(int idPage) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Pages> pages = session.createCriteria(Pages.class).add(Restrictions.eq("id", idPage)).list();
        session.getTransaction().commit();
        session.close();
        return pages.get(0);
    }

    @Override
    public List<Pages> getFreshPagesint (int idSite, int countPages) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Pages> pages = session.createCriteria(Pages.class).add(Restrictions.eq("siteId", idSite)).addOrder(Order.desc("foundDateTime")).setMaxResults(countPages).list();
        session.getTransaction().commit();
        session.close();
        return pages;
    }
}
