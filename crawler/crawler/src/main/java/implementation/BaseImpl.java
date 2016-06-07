package implementation;

import interfaces.BaseInterface;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alexey_n on 02.06.2016.
 */
public abstract class BaseImpl<T> implements BaseInterface<T> {
    @Override
    public void addEntity(T entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addListEntity(List<T> listEntity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
//        session.save(listEntity);
        for (T entity : listEntity) {
            session.save(entity);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeEntity(T entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveOrUpdateEntity(T entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<T> getAllEntity(T entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<T> listEntity = session.createCriteria(entity.getClass()).list();
        session.getTransaction().commit();
        session.close();
        return listEntity;
    }
}
