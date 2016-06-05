package statPerson.crauler.elements.main.java;

import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by alexey_n on 28.05.2016.
 */
public interface Repository<T> {
    Integer addEntity(T entity);
    
    public T getEntityById(Integer Id);

    void removeEntity(T entity);

    void removeEntityById(Integer Id);
    
    void updateEntity(T entity);

    List<T> query(Criteria criteria);
}