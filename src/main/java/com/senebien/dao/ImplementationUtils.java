package main.java.com.senebien.dao;

import org.hibernate.Session;

public class ImplementationUtils {

    public static boolean create(Session session, Object o) {
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Session session, Object o) {
        try {
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
