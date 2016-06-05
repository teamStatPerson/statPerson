package statPerson.elements.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import statPerson.Factory;
import statPerson.element.account.Account;
import statPerson.elements.Sites;

public class SitesDao {
	public static Integer addSite(Sites site) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer idSite = null;

		try {
			tx = session.beginTransaction();
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

	public static Sites getSite(Integer idSite) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Sites site = null;

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Sites.class);
			criteria.add(Restrictions.eq("ID", idSite));

			List<Sites> sites = (List<Sites>) criteria.list();
			if (sites.size() == 0) {
				return null;
			}
			site = sites.get(0);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return site;
	}

	public static void removeSite(Integer idSite) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Account.class);
			criteria.add(Restrictions.eq("ID", idSite));

			List<Sites> sites = (List<Sites>) criteria.list();
			for (int i = 0; i < sites.size(); i++) {
				session.delete(sites.get(i));
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
