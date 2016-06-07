package interfaces;

import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by alexey_n on 28.05.2016.
 */
public interface BaseInterface<T> {
    void addEntity(T entity);

    void addListEntity(List<T> listEntity);

    void removeEntity(T entity);

    void saveOrUpdateEntity(T entity);

    List<T> getAllEntity(T entity);
}