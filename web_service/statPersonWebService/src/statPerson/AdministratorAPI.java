package statPerson;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import elements.Administrator;
import exceptions.AdministratorManyAccounts;
import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;

public class AdministratorAPI {

	public static Administrator getAdministrator(Integer idAdministrator)
			throws AdministratorNotExist, AdministratorManyAccounts, NotCorrectInputData {
		if (idAdministrator == null) {
			throw new NotCorrectInputData();
		}
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Administrator administrator = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Administrator.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("id"), "idAdministrator");
			criteria.setProjection(projList);

			List<Administrator> administrators = (List<Administrator>) criteria.list();
			if (administrators.size() == 0) {
				throw new AdministratorNotExist();
			} else if (administrators.size() > 1) {
				throw new AdministratorManyAccounts();
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
	}

	public static Administrator getAdministrator(String email, String password)
			throws AdministratorNotExist, AdministratorManyAccounts, NotCorrectInputData {
		if (email == null || password == null) {
			throw new NotCorrectInputData();
		}
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Administrator administrator = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Administrator.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("email"), "email");
			projList.add(Projections.property("password"), "password");
			criteria.setProjection(projList);

			List<Administrator> administrators = (List<Administrator>) criteria.list();
			if (administrators.size() == 0) {
				throw new AdministratorNotExist();
			} else if (administrators.size() > 1) {
				throw new AdministratorManyAccounts();
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

	public static Integer addPrimaryAdministrator(String email, String password)
			throws AdministratorManyAccounts, NotCorrectInputData {
		if (email == null || password == null) {
			throw new NotCorrectInputData();
		}
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer idAdministrator = null;

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Administrator.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("email"), "email");
			projList.add(Projections.property("password"), "password");
			criteria.setProjection(projList);

			List<Administrator> administrators = (List<Administrator>) criteria.list();
			if (administrators.size() == 0) {
				Administrator administrator = new Administrator(email, password, Utils.getCurrentTime(), false);
				idAdministrator = (Integer) session.save(administrator);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return idAdministrator;
	}

	public static void removeAdministrator(Integer idAdministrator) throws NotCorrectInputData {
		if (idAdministrator == null) {
			throw new NotCorrectInputData();
		}
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(idAdministrator);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/*
	 * // Persons // return null, if haven`t persons Person[]
	 * getPersons(Administrator administrator) { };
	 * 
	 * void addPerson(Administrator administrator, Person person) { };
	 * 
	 * void removePerson(Administrator administrator, Person person) { };
	 * 
	 * // Sites // return null, if haven`t sites Site[] getSites(Administrator
	 * administrator) { };
	 * 
	 * void addSite(Administrator administrator, Site site) { };
	 * 
	 * void removeSite(Administrator administrator, Site site) { };
	 * 
	 * // Pages for crauler statistic // return null, if haven`t pages Page[]
	 * getPages(Site site) { };
	 * 
	 * // Keywords // return null, if haven`t keywords Keyword[]
	 * getKeywords(Administrator administrator, Person person) { };
	 * 
	 * void addKeyword(Administrator administrator, Keyword keyword) { };
	 * 
	 * void removeKeyword(Administrator administrator, Keyword keyword) { };
	 */
}