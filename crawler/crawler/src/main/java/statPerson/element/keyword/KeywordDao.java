package statPerson.element.keyword;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import statPerson.Factory;

public class KeywordDao {

	public static Integer addKeyword(String name, int idPerson) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer id = null;

		try {
			tx = session.beginTransaction();

			Keyword keyword = new Keyword(name, idPerson);
			id = (Integer) session.save(keyword);

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

	@SuppressWarnings("unchecked")
	public static List<Keyword> getKeywordOfPerson(Integer idPerson) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<Keyword> keywors = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Keyword.class);
			criteria.add(Restrictions.eq("personId", idPerson));

			keywors = (List<Keyword>) criteria.list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return keywors;
	}

	@SuppressWarnings("unchecked")
	public static void removeKeywordFromPerson(Integer idKeyword) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Keyword.class);
			criteria.add(Restrictions.eq("id", idKeyword));

			List<Keyword> keywords = (List<Keyword>) criteria.list();
			for (int i = 0; i < keywords.size(); i++) {
				session.delete(keywords.get(i));
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
