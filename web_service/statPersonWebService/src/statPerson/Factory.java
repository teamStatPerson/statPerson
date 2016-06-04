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

/*
 * // public class HibernateSessionFactory {
 * 
 * private static final SessionFactory factory = buildSessionFactory();
 * 
 * private static SessionFactory buildSessionFactory() { Configuration
 * configuration = new Configuration().configure(); // configuration // settings
 * // from // hibernate.cfg.xml
 * 
 * configuration.addAnnotatedClass(statPerson.elements.Sites.class);
 * StandardServiceRegistryBuilder serviceRegistryBuilder = new
 * StandardServiceRegistryBuilder();
 * 
 * serviceRegistryBuilder.applySettings(configuration.getProperties());
 * 
 * ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
 * 
 * return configuration.buildSessionFactory(serviceRegistry); }
 * 
 * public static SessionFactory getSessionFactory() { return factory; }
 * 
 * public static void shutdown() { // Close caches and connection pools
 * getSessionFactory().close(); } }
 */