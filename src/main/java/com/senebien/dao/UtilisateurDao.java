package main.java.com.senebien.dao;

import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.models.Utilisateur;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 *
 * Class that implement the IUtilisateurDao interface
 */
@Stateless
public class UtilisateurDao implements IUtilisateurDao {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Session session = HibernateInitializerConfig.getSession();

    @Override
    public boolean create(Utilisateur user) {
        try {
            session.save(user);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Utilisateur user) {
        try {
            session.update(user);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Utilisateur> all() {
        try {
            return session.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Utilisateur> allActivatedUser() {
        try {
            return session.createQuery("select u from Utilisateur u where u.status = true ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Utilisateur> allDesactivatedUser() {
        try {
            return session.createQuery("select u from Utilisateur u where u.status = false ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Utilisateur> allArchivedUser() {
        try {
            return session.createQuery("select u from Utilisateur u where u.archive = true ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Utilisateur> allUnarchivedUser() {
        try {
            return session.createQuery("select u from Utilisateur u where u.archive = false ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Utilisateur getUserById(Long id) {
        try {
            return session.createQuery("select u from Utilisateur u where u.id=:id", Utilisateur.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }

    @Override
    public Utilisateur getUserByUsername(String username) {
        try {
            Utilisateur utilisateur = session.createQuery("select u from Utilisateur u where u.username=:username", Utilisateur.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return utilisateur != null && utilisateur.getId() != null ? utilisateur : null;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }

    @Override
    public Utilisateur getUserByUsernameAndPasswordAndProfile(String login, String password) {
        try {
            Utilisateur utilisateur =  session.createQuery("select u from Utilisateur u where u.archive = false and u.status = true" +
                    " and u.username=:login and u.password=:password", Utilisateur.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            return utilisateur != null && utilisateur.getId() != null ? utilisateur : null;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }
}
