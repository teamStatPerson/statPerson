import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alexey_n on 28.05.2016.
 */
public class RepositorySP<T> implements Repository<T> {

    public void addEntity(T entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    public void removeEntity(T entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public void updateEntity(T entity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }

    public List<T> query(Criteria criteria) {

        return null;
        //        return HibernateUtil.getSession().createCriteria(entity.getClass()).list();
    }
}