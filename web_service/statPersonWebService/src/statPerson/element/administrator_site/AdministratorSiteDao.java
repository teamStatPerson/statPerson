package statPerson.element.administrator_site;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import statPerson.Factory;
import statPerson.element.account.AccountDao;
import statPerson.element.site.Site;
import statPerson.element.site.SiteDao;

public class AdministratorSiteDao {

	public static Integer addAdministratorSite(Integer idAdministrator, Integer idSite) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();

			if (AccountDao.isAdministator(idAdministrator)) {
				AdministratorSite site = new AdministratorSite(idAdministrator, idSite);
				id = (Integer) session.save(site);
			}

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


	public static AdministratorSite getAdministratorSiteById(Integer idAdministratorSite) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		AdministratorSite adminSite = null;
		try {
			tx = session.beginTransaction();
			
			adminSite = (AdministratorSite) session.get(AdministratorSite.class, idAdministratorSite);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return adminSite;
	};
	
	@SuppressWarnings("unchecked")
	public static List<Site> getAllSitesAdministrator(Integer idAdministrator) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<Site> sites = new ArrayList<Site>();
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(AdministratorSite.class);
			criteria.add(Restrictions.eq("idAdministrator", idAdministrator));

			List<AdministratorSite> adminSite = (List<AdministratorSite>) criteria.list();
			for (int i = 0; i < adminSite.size(); i++) {
				sites.add(SiteDao.getSite(adminSite.get(i).getIdSite()));
			}

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

	@SuppressWarnings("unchecked")
	public static void removeSiteFromAdministrator(Integer idAdministrator, Integer idSite) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			if (AccountDao.isAdministator(idAdministrator)) {
				Criteria criteria = session.createCriteria(AdministratorSite.class);
				criteria.add(Restrictions.eq("idAdministrator", idAdministrator));
				criteria.add(Restrictions.eq("idSite", idSite));

				List<AdministratorSite> sites = (List<AdministratorSite>) criteria.list();
				for (int i = 0; i < sites.size(); i++) {
					session.delete(sites.get(i));
				}
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
