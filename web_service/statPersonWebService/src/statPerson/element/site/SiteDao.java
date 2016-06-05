package statPerson.element.site;

import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import statPerson.Factory;

public class SiteDao {

	public static Integer addSite(String URLname, Calendar startDateStatistics) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer idSite = null;

		try {
			tx = session.beginTransaction();

			Site site = new Site(URLname, startDateStatistics);
			idSite = (Integer) session.save(site);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return idSite;
	}

	public static Site getSite(Integer idSite) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Site site = null;
		try {
			tx = session.beginTransaction();
			
			site = (Site) session.get(Site.class, idSite);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return site;
	};

	public static void removeSite(Integer idSite){
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Site site = (Site) session.get(Site.class, idSite);
			session.delete(site);
			
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
