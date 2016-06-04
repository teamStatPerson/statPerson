package statPerson.element.administrator;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;
import statPerson.Factory;
import statPerson.Utils;

public class AdministratorDao {
	
	public Administrator getAdministrator(String email, String password)
			throws AdministratorNotExist, NotCorrectInputData {
		if (email == null || password == null) {
			throw new NotCorrectInputData();
		}
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Administrator administrator = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Administrator.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.add(Restrictions.eq("password", password));

			List<Administrator> administrators = (List<Administrator>) criteria.list();
			if (administrators.size() == 0) {
				throw new AdministratorNotExist();
			}
			administrator = administrators.get(0);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return administrator;
	};

	public Administrator addPrimaryAdministrator(String email, String password)
			throws  NotCorrectInputData {
		if (email == null || password == null) {
			throw new NotCorrectInputData();
		}
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Administrator administrator = null;

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Administrator.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.add(Restrictions.eq("password", password));

			List<Administrator> administrators = (List<Administrator>) criteria.list();
			if (administrators.size() == 0) {
				administrator = new Administrator(email, password, Utils.getCurrentTime(), false);
				session.save(administrator);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		try {
			if (administrator != null)
				return getAdministrator(email, password);
		} catch (AdministratorNotExist e) {
			e.printStackTrace();
		}
		return null;
	}

	public void removeAdministrator(String email, String password) throws NotCorrectInputData {
		if (isExistAdministrator(email, password)) {
			Session session = Factory.getFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();

				Criteria criteria = session.createCriteria(Administrator.class);
				criteria.add(Restrictions.eq("email", email));
				criteria.add(Restrictions.eq("password", password));

				List<Administrator> administrators = (List<Administrator>) criteria.list();
				for (int i = 0; i < administrators.size(); i++) {
					session.delete(administrators.get(i));
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

	private static boolean isExistAdministrator(String email, String password) throws NotCorrectInputData {
		if (email == null || password == null) {
			throw new NotCorrectInputData();
		}
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		boolean isExist = false;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Administrator.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.add(Restrictions.eq("password", password));

			List<Administrator> administrators = (List<Administrator>) criteria.list();
			if (administrators.size() > 0) {
				isExist = true;
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isExist;
	}
}