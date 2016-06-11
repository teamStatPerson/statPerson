package statPerson;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {

    private static SessionFactory factory = null;

    public static SessionFactory getFactory() {
        if (factory == null) {
            try {
                factory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return factory;
    }
}