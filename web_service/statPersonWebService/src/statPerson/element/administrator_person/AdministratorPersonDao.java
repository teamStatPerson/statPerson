package statPerson.element.administrator_person;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import statPerson.Factory;
import statPerson.element.account.Account;
import statPerson.element.account.AccountDao;

public class AdministratorPersonDao {

	public static void addPersonToAdministrator(Integer idAdministrator, Integer idPerson) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			if (AccountDao.isAdministator(idAdministrator)) {
				AdministratorPerson adminPerson = new AdministratorPerson(idAdministrator, idPerson);
				session.save(adminPerson);
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

	public static List<AdministratorPerson> getAllPersonAccount(Integer idAccount) {

		Account account = AccountDao.getAccountById(idAccount);
		List<AdministratorPerson> persons = new ArrayList<AdministratorPerson>();

		persons.addAll((List<AdministratorPerson>) getPersonsAccount(idAccount));
		if (!account.isPrimaryAdministrator()) {
			persons.addAll((List<AdministratorPerson>) getAllPersonAccount(account.getIdLinkedAdministrator()));
		}

		return persons;
	}

	@SuppressWarnings("unchecked")
	private static List<AdministratorPerson> getPersonsAccount(Integer idAccount) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<AdministratorPerson> persons = new ArrayList<AdministratorPerson>();

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(AdministratorPerson.class);
			criteria.add(Restrictions.eq("idAccount", idAccount));
			persons.addAll((List<AdministratorPerson>) criteria.list());

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return persons;
	}

	public static boolean removePersonFromAdministrator(Integer idAdministrator, Integer idPerson) {
		List<AdministratorPerson> persons = getPersonsAccount(idAdministrator);
		for(int i=0;i<persons.size();i++){
			if(persons.get(i).getIdPerson() == idPerson){

				Session session = Factory.getFactory().openSession();
				Transaction tx = null;
				try {
					tx = session.beginTransaction();

					AdministratorPerson AdPerson = (AdministratorPerson) session.get(AdministratorPerson.class, idPerson);
					session.delete(AdPerson);
					
					tx.commit();
				} catch (HibernateException e) {
					if (tx != null)
						tx.rollback();
					e.printStackTrace();
				} finally {
					session.close();
				}
				return true;
			}
		}
		return false;
	}
}
