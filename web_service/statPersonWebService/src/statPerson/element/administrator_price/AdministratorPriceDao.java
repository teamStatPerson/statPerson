package statPerson.element.administrator_price;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import statPerson.Factory;
import statPerson.Utils;
import statPerson.element.administrator.Administrator;

public class AdministratorPriceDao {

	public static void addPriceToAdministrator(Integer idAdministrator, Integer idPrice) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			AdministratorPrice adPrice = new AdministratorPrice(idAdministrator, idPrice, Utils.getCurrentTime());
			session.save(adPrice);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<AdministratorPrice> getAllPricesAdministrator(Integer idAdministrator) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<AdministratorPrice> prices = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(AdministratorPrice.class);
			criteria.add(Restrictions.eq("idAdministrator", idAdministrator));

			prices = (List<AdministratorPrice>) criteria.list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return prices;
	}
}
