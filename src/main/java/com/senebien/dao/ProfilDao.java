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
            session.persist(profil);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Profil profil) {
        try {
            session.update(profil);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Profil> all() {
        try {
            return session.createQuery("select p from Profil p", Profil.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Profil> allByStatusProfil(Boolean status) {
        try {
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
            return session.createQuery("select p from Profil p where p.id=:id", Profil.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Profil getProfilByLibelle(String libelle) {
        try {
            return session.createQuery("select p from Profil p where p.libelle like :libelle" +
                    " and p.archiver=false and p.status=true ", Profil.class)
                    .setParameter("libelle", libelle)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
