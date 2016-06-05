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

	
//
//private static final SessionFactory sessionFactory;
//    static {
//        try {
////            sessionFactory = new AnnotationConfiguration()
////            		.addAnnotatedClass(statPerson.crauler.elements.main.java.entity.Pages)
////                    .configure()
////                    .buildSessionFactory();
//        } catch (Throwable ex) {
//            // Log exception!
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession()
//            throws HibernateException {
//        return sessionFactory.openSession();
//    }
    
//
//	private static SessionFactory factory = null;
//    private static SessionFactory sessionFactory;
//    //private static ServiceRegistry serviceRegistry;
//
//    static {
//        try
//        {
//            //Configuration configuration = new Configuration().configure();
//            //serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//            //sessionFactory = configuration.buildSessionFactory(serviceRegistry);
////        	Configuration cfg=new Configuration().configure();
////        	StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().applySettings(
////        	            cfg.getProperties());
////        	factory= cfg.buildSessionFactory(builder.build());
//        	Configuration config = new Configuration();
//        	config.configure();
//        	ServiceRegistryBuilder srBuilder = new ServiceRegistry();
//        	srBuilder.applySettings(config.getProperties());
//        	ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
//        	factory = config.buildSessionFactory(serviceRegistry);
//        }
//        catch (HibernateException exception)
//        {
//            System.out.println("Problem creating session factory");
//        }
//    }
//    public static SessionFactory getFactory()
//    {
//        return sessionFactory;
//    }
    
//
//	private static SessionFactory factory = null;
//    private static SessionFactory sessionFactory;
//    private static ServiceRegistry serviceRegistry;
//
//	public static SessionFactory getFactory() {
//		if (factory == null) {
//			try {
//				// factory = new
//				// Configuration().configure().buildSessionFactory();
//
//				Configuration configuration = new Configuration().configure();
//				serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
//						.buildServiceRegistry();
//				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//			} catch (Throwable ex) {
//				System.err.println("Failed to create sessionFactory object." + ex);
//				throw new ExceptionInInitializerError(ex);
//			}
//		}
//		return factory;
//	}
}