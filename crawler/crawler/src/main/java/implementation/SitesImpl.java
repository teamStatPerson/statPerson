package implementation;

import entity.Sites;
import interfaces.SitesInterface;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by alexey_n on 02.06.2016.
 */
public class SitesImpl extends BaseImpl<Sites> implements SitesInterface{
    @Override
    public Sites getSiteByID(int idSite) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Sites> site = session.createCriteria(Sites.class).add(Restrictions.eq("id", idSite)).list();
        session.getTransaction().commit();
        session.close();
        return site.get(0);
    }
}
