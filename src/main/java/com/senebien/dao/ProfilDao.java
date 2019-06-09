package main.java.com.senebien.dao;

import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.models.Profil;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProfilDao implements IProfilDao {
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
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Profil> all() {
        try {
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            List<Profil> profils;
            session.beginTransaction();
            profils = session.createQuery("select p from Profil p", Profil.class).getResultList();
            session.getTransaction().commit();
            return profils;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Profil> allByStatusProfil(Boolean status) {
        try {
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            return session.createQuery("select p from Profil p where p.status=:status and p.archiver=false ", Profil.class)
                    .setParameter("status", status)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Profil> allByArchivedProfil(Boolean archive) {
        try {
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            return session.createQuery("select p from Profil p where p.archiver=:archive ", Profil.class)
                    .setParameter("archive", archive)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Profil getOneById(Long id) {
        try {
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            return session.createQuery("select p from Profil p where p.id=:id", Profil.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Profil getProfilByLibelle(String libelle) {
        try {
            Profil profil;
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            profil = session.createQuery("select p from Profil p where p.libelle like :libelle" +
                    " and p.archiver=false and p.status=true ", Profil.class)
                    .setParameter("libelle", libelle)
                    .getSingleResult();
            session.getTransaction().commit();
            return profil;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
