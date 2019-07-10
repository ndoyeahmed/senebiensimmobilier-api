package main.java.com.senebien.config;

import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HibernateInitializerConfig {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate-postgres.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return ourSessionFactory.openSession();
    }


    void initDatabase() {
        final Session session = getSession();
        try {
            LOGGER.log(Level.INFO, "querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                LOGGER.log(Level.INFO, "executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    LOGGER.log(Level.SEVERE, "Something went wrong: {0} ", o);
                }
            }
        } finally {
            session.close();
        }
    }
}
