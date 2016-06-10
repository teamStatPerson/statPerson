package statPerson.element.site;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.criterion.Restrictions;
import statPerson.Factory;

public class SiteDao {

	public static Integer addSite(String URLname, Date startDateStatistics) {
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

	public static void updateSite(Site site) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(site);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static List<Site> getAllNewSites() {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<Site> newSites = null;
		try {
			tx = session.beginTransaction();
			newSites = session.createCriteria(Site.class).add(Restrictions.isNull("startDateStatistics")).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return newSites;
	}

	public static List<Site> getAllSites() {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<Site> sites = null;
		try {
			tx = session.beginTransaction();
			sites = session.createCriteria(Site.class).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sites;
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
