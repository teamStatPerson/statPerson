package implementation;

import entity.Keywords;
import interfaces.KeywordsInterface;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;


/**
 * Created by alexey_n on 02.06.2016.
 */
public class KeywordsImpl extends BaseImpl<Keywords> implements KeywordsInterface {
    @Override
    public List<Keywords> getKeywordsByIDPerson(int personId) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Keywords> keywords = session.createCriteria(Keywords.class).add(Restrictions.eq("personId", personId)).list();
        session.getTransaction().commit();
        session.close();
        return keywords;
    }

}
