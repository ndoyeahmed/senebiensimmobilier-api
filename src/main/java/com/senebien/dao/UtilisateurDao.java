package main.java.com.senebien.dao;

import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.models.Utilisateur;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 *
 * Class that implement the IUtilisateurDao interface
 */
@Stateless
public class UtilisateurDao implements IUtilisateurDao {
    private Session session = HibernateInitializerConfig.getSession();

    @Override
    public boolean create(Utilisateur user) {
        try {
            session.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Utilisateur user) {
        try {
            session.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Utilisateur> all() {
        try {
            /*if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }*/
            return session.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> allActivatedUser() {
        try {
           /* if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }*/
            return session.createQuery("select u from Utilisateur u where u.status = true ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> allDesactivatedUser() {
        try {
            /*if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }*/
            return session.createQuery("select u from Utilisateur u where u.status = false ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> allArchivedUser() {
        try {
            /*if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }*/
            return session.createQuery("select u from Utilisateur u where u.archive = true ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> allUnarchivedUser() {
        try {
            /*if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }*/
            return session.createQuery("select u from Utilisateur u where u.archive = false ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Utilisateur getUserById(Long id) {
        try {
            /*if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }*/
            return session.createQuery("select u from Utilisateur u where u.id=:id", Utilisateur.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Utilisateur getUserByUsername(String username) {
        try {
            /*if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }*/
            Utilisateur utilisateur = session.createQuery("select u from Utilisateur u where u.username=:username", Utilisateur.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return utilisateur != null && utilisateur.getId() != null ? utilisateur : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Utilisateur getUserByUsernameAndPasswordAndProfile(String login, String password) {
        try {
           /* if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }*/
            Utilisateur utilisateur =  session.createQuery("select u from Utilisateur u where u.archive = false and u.status = true" +
                    " and u.username=:login and u.password=:password", Utilisateur.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            return utilisateur != null && utilisateur.getId() != null ? utilisateur : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
