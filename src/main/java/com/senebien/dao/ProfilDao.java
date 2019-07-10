package main.java.com.senebien.dao;

import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.models.Profil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//@Stateless
public class ProfilDao implements IProfilDao {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Session session = HibernateInitializerConfig.getSession();

    @Override
    public boolean create(Profil profil) {
        try {
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            session.save(profil);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Profil profil) {
        try {
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            session.update(profil);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.log(Level.INFO, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Profil> all() {
        try {
            List<Profil> profils;
            profils = session.createQuery("select p from Profil p", Profil.class).getResultList();
            return profils;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Profil> allByStatusProfil(Boolean status) {
        try {
            return session.createQuery("select p from Profil p where p.status=:status and p.archiver=false ", Profil.class)
                    .setParameter("status", status)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Profil> allByArchivedProfil(Boolean archive) {
        try {
            return session.createQuery("select p from Profil p where p.archiver=:archive ", Profil.class)
                    .setParameter("archive", archive)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Profil getOneById(Long id) {
        try {
            return session.createQuery("select p from Profil p where p.id=:id", Profil.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }

    @Override
    public Profil getProfilByLibelle(String libelle) {
        try {
            Profil profil;
            profil = session.createQuery("select p from Profil p where p.libelle like :libelle" +
                    " and p.archiver=false and p.status=true ", Profil.class)
                    .setParameter("libelle", libelle)
                    .getSingleResult();
            return profil;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return null;
        }
    }
}
