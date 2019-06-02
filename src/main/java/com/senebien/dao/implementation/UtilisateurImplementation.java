package main.java.com.senebien.dao.implementation;

import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.dao.interfaces.IUtilisateur;
import main.java.com.senebien.models.Profil;
import main.java.com.senebien.models.Utilisateur;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 *
 * Class that implement the IUtilisateur interface
 */
@Stateless
public class UtilisateurImplementation implements IUtilisateur {

    private Session session = HibernateInitializerConfig.getSession();

    @Override
    public boolean create(Utilisateur user) {
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Utilisateur user) {
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Utilisateur> all() {
        try {
            return session.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> allActivatedUser() {
        try {
            return session.createQuery("select u from Utilisateur u where u.status = true ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> allDesactivatedUser() {
        try {
            return session.createQuery("select u from Utilisateur u where u.status = false ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> allArchivedUser() {
        try {
            return session.createQuery("select u from Utilisateur u where u.archive = true ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> allUnarchivedUser() {
        try {
            return session.createQuery("select u from Utilisateur u where u.archive = false ", Utilisateur.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Utilisateur getUserById(Long id) {
        try {
            return session.createQuery("select u from Utilisateur u where u.id=:id", Utilisateur.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean getUserByUsernameAndPasswordAndProfile(String login, String password, Profil profil) {
        try {
            Utilisateur utilisateur =  session.createQuery("select u from Utilisateur u where u.archive = false and u.status = true" +
                    " and u.username=:login and u.password=:password and u.profil.id=:profil" +
                    " and u.profil.status=true and u.profil.archiver=false ", Utilisateur.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .setParameter("profil", profil.getId())
                    .getSingleResult();
            if (utilisateur != null && utilisateur.getId() != null)
                return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
