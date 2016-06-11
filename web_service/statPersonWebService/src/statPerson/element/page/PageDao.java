package statPerson.element.page;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import statPerson.Factory;
import statPerson.element.person.Person;

public class PageDao {

	private static Integer addPageById(String url, int siteId, Calendar foundDateTime, Calendar lastScanDate,
			String html) {
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

	public static Page addPage(String url, int siteId, Calendar foundDateTime, Calendar lastScanDate, String html) {
		Integer id = addPageById(url, siteId, foundDateTime, lastScanDate, html);
		return getById(id);
	}

	@SuppressWarnings("unchecked")
	public static Page getById(Integer id) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Page page = null;

		try {
			tx = session.beginTransaction();

			page = (Page) session.get(Page.class, id);

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

	@SuppressWarnings("unchecked")
	public static Page getByPage(String url) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Page page = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Page.class);
			criteria.add(Restrictions.eq("url", url));

			List<Page> pages = (List<Page>) criteria.list();
			if (pages.size() > 0) {
				page = pages.get(0);
			}

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

	public static Page updatePage(int pageId, String html) {

		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer id = null;

		try {
			tx = session.beginTransaction();

			Page page = (Page) session.get(Page.class, pageId);
			page.setHtml(html);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getById(pageId);
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
