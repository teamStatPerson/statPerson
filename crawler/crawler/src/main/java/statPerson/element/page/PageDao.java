package statPerson.element.page;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import statPerson.Factory;

import java.util.Date;
import java.util.List;

public class PageDao {

    public static Integer addPages(String url, int siteId, Date foundDateTime, Date lastScanDate, String html) {
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

    public static Date getMinimumDateForSite(int idSite) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
        Date minimumDate = null;
        try {
            tx = session.beginTransaction();
            Page page = (Page) session.createCriteria(Page.class).add(Restrictions.eq("siteId",idSite)).addOrder(Order.asc("foundDateTime")).setMaxResults(1).uniqueResult();
            minimumDate = page.getFoundDateTime();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return minimumDate;
    }

    public static List<Page> getNewestPageForSite(int idSite, int count) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
        List<Page> newestPages= null;
        try {
            tx = session.beginTransaction();
            newestPages = session.createCriteria(Page.class).add(Restrictions.isNull("lastScanDate")).add(Restrictions.eq("siteId",idSite)).addOrder(Order.desc("foundDateTime")).setMaxResults(count).list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return newestPages;
    }


    public static void updatePage(Page page) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(page);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Page getPageById(int idPage) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
        Page page = null;
        try {
            tx = session.beginTransaction();
            page = (Page) session.createCriteria(Page.class).add(Restrictions.eq("id", idPage)).uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return page;
    }

    public static void addPages(List<Page> pages) {
        Session session = Factory.getFactory().openSession();
        Transaction tx = null;
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
