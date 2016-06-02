import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by alexey_n on 28.05.2016.
 */
public interface Repository<T> {
    void addEntity(T entity);

    void removeEntity(T entity);

    void updateEntity(T entity);

    List<T> query(Criteria criteria);
}