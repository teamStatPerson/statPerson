package statPerson.crauler.elements.main.java;

import org.hibernate.Criteria;
import org.hibernate.Session;

import statPerson.Factory;

import java.util.List;

/**
 * Created by alexey_n on 28.05.2016.
 */
public class RepositorySP<T> implements Repository<T> {

    public Integer addEntity(T entity) {
    	Integer id = null;
        Session session = Factory.getFactory().openSession();
        session.beginTransaction();
        id = (Integer) session.save(entity);
        session.getTransaction().commit();
        return id;
    }

    public void removeEntity(T entity) {
        Session session = Factory.getFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public void updateEntity(T entity) {
        Session session = Factory.getFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }

	@SuppressWarnings("unchecked")
	public T getEntityById(Integer Id) {
		T entity = null;
        Session session = Factory.getFactory().openSession();
        session.beginTransaction();
        entity = (T) session.get(RepositorySP.class, Id);
        session.getTransaction().commit();
        return entity;
	}

	@SuppressWarnings("unchecked")
	public void removeEntityById(Integer Id) {
        Session session = Factory.getFactory().openSession();
        session.beginTransaction();
		T entity = (T) session.get(RepositorySP.class, Id);
        session.delete(entity);
        session.getTransaction().commit();
	}
	
    public List<T> query(Criteria criteria) {

        return null;
    }

}