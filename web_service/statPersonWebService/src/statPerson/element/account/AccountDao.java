package statPerson.element.account;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import exceptions.NotCorrectInputData;
import statPerson.Factory;
import statPerson.Utils;

public class AccountDao {

	private static Integer addAccount(String email, String password, int type_account, int linked_administrator)
			throws  NotCorrectInputData {
		if (email == null || password == null) {
			throw new NotCorrectInputData();
		}
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer idAccount = null;

		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Account.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.add(Restrictions.eq("password", password));

			List<Account> accounts = (List<Account>) criteria.list();
			if (accounts.size() == 0) {
				Account account = new Account(email, password, Utils.getCurrentTime(),type_account,linked_administrator);
				idAccount = (Integer) session.save(account);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return idAccount;
	}
	
	public static Integer addPrimaryAdministrator(String email, String password) throws NotCorrectInputData{
		return addAccount( email,  password,  Account.PRIMARY_ADMINISTRATOR_ACCOUNT,  Account.DEFAULF_EMPTY_LINKED_ADMINISRATOR);
	}
	
	public static Integer addSecondaryAdministrator(String email, String password, int idLinkedAdministrator) throws NotCorrectInputData{
		return addAccount( email,  password,  Account.SECONDARY_ADMINISTRATOR_ACCOUNT,  idLinkedAdministrator);
	}
	
	public static Integer addUser(String email, String password, int idLinkedAdministrator) throws NotCorrectInputData{
		return addAccount( email,  password,  Account.USER_ACCOUNT,  idLinkedAdministrator);
	}
	

	public static Account getAccount(Integer idAccount) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Account account = null;
		try {
			tx = session.beginTransaction();
			
			account = (Account) session.get(Account.class, idAccount);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	};
	

	public static void removeAccount(Integer idAccount){
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Account account = (Account) session.get(Account.class, idAccount);
			session.delete(account);
			
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