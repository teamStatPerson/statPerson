package statPerson.element.person;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import statPerson.Factory;
import statPerson.element.price.Price;

public class PersonDao {

	public static Integer addPerson(String name) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Integer id = null;

		try {
			tx = session.beginTransaction();

			Person person = new Person(name);
			id = (Integer) session.save(person);

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

	public static Person getPerson(Integer idPerson) {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		Person person = null;
		try {
			tx = session.beginTransaction();
			
			person = (Person) session.get(Person.class, idPerson);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return person;
	}

	public static List<Person> getAllPerson() {
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		List<Person> persons = null;
		try {
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(Person.class);

			persons = (List<Person>) criteria.list();

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

	public static void removePerson(Integer idPerson){
		Session session = Factory.getFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Person person = (Person) session.get(Person.class, idPerson);
			session.delete(person);
			
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
