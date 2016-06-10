package statPerson.element.page;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import statPerson.Factory;

import java.util.Date;
import java.util.List;

public class PageDao {

    public static Integer addPage(String url, int siteId, Date foundDateTime, Date lastScanDate, String html) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
        Integer id = null;

        try {
            tx = session.beginTransaction();

            Page page = new Page(url, siteId, foundDateTime, lastScanDate, html);
            id = (Integer) session.save(page);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public static void addPage(List<Page> pages) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
        Integer id = null;

        try {
            tx = session.beginTransaction();

            for (Page page : pages) {
                session.save(page);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @SuppressWarnings("unchecked")
    public static List<Page> getAllPage(Integer siteId) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
        List<Page> pages = null;
        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Page.class);
            criteria.add(Restrictions.eq("siteId", siteId));

            pages = (List<Page>) criteria.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pages;
    }

    @SuppressWarnings("unchecked")
    public static void removeAllPage(Integer siteId) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Page.class);
            criteria.add(Restrictions.eq("siteId", siteId));

            List<Page> pages = (List<Page>) criteria.list();
            for (int i = 0; i < pages.size(); i++) {
                session.delete(pages.get(i));
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
