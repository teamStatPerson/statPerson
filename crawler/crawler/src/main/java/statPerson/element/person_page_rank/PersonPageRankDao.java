package statPerson.element.person_page_rank;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import statPerson.Factory;

public class PersonPageRankDao {

	public static Integer addPersonPageRank(int personId, int pageId, int rank) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer id = null;

		try {
			tx = session.beginTransaction();

			PersonPageRank ppr = new PersonPageRank(personId, pageId, rank);
			id = (Integer) session.save(ppr);

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

	public static void saveOrUpdate(PersonPageRank personPageRank) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(personPageRank);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static PersonPageRank getByPersonPage(int idPerson, int idPage) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		PersonPageRank personPageRank = null;
		try {
			tx = session.beginTransaction();
			personPageRank = (PersonPageRank)session.createCriteria(PersonPageRank.class).add(Restrictions.eq("personId", idPerson)).add(Restrictions.eq("pageId", idPage)).uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return personPageRank;
	}

	@SuppressWarnings("unchecked")
	public static List<PersonPageRank> getPersonPageRankByPage(Integer pageId) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<PersonPageRank> ppr = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(PersonPageRank.class);
			criteria.add(Restrictions.eq("pageId", pageId));

			ppr = (List<PersonPageRank>) criteria.list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ppr;
	}

	@SuppressWarnings("unchecked")
	public static List<PersonPageRank> getPersonPageRankByPerson(Integer personId) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<PersonPageRank> ppr = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(PersonPageRank.class);
			criteria.add(Restrictions.eq("personId", personId));

			ppr = (List<PersonPageRank>) criteria.list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ppr;
	}

	public static void removePersonPageRank(Integer id){
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			PersonPageRank ppr = (PersonPageRank) session.get(PersonPageRank.class, id);
			session.delete(ppr);
			
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
