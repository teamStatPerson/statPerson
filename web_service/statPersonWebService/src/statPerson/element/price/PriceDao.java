package statPerson.element.price;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import exceptions.AdministratorNotExist;
import exceptions.NotCorrectInputData;
import statPerson.Factory;
import statPerson.Utils;
import statPerson.element.administrator.Administrator;

public class PriceDao {

	public static Integer addPrice(String name, int maxAmountUsers, int maxAmountSites, int durationOfPriceDay) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer idPrice = null;

		try {
			tx = session.beginTransaction();

			Price price = new Price(name, maxAmountUsers, maxAmountSites, durationOfPriceDay);
			idPrice = (Integer) session.save(price);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return idPrice;
	}

	public static Price getPrice(Integer idPrice) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Price price = null;
		try {
			tx = session.beginTransaction();
			
			price = (Price) session.get(Price.class, idPrice);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return price;
	};

	public static List<Price> getPrices() {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<Price> prices = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Price.class);

			prices = (List<Price>) criteria.list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return prices;
	};

	public static void removePrice(Integer idPrice){
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Price price = (Price) session.get(Price.class, idPrice);
			session.delete(price);
			
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
