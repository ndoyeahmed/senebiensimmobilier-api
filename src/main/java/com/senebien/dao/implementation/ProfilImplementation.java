package main.java.com.senebien.dao.implementation;

import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.dao.interfaces.IProfil;
import main.java.com.senebien.models.Profil;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 */
@Stateless
public class ProfilImplementation implements IProfil {
    private Session session = HibernateInitializerConfig.getSession();

    @Override
    public boolean create(Profil profil) {
       try {
           session.beginTransaction();
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
        return false;
    }

    @Override
    public List<Profil> all() {
        return null;
    }

    @Override
    public List<Profil> allActivatedProfil() {
        return null;
    }

    @Override
    public List<Profil> allDisabledProfil() {
        return null;
    }

    @Override
    public List<Profil> allArchivedProfil() {
        return null;
    }

    @Override
    public List<Profil> allNoneArchivedProfil() {
        return null;
    }

    @Override
    public Profil getOneById(Long id) {
        return null;
    }

    @Override
    public Profil getProfilByLibelle(String libelle) {
        try {
            return session.createQuery("select p from Profil p where p.libelle like :libelle", Profil.class)
                    .setParameter("libelle", libelle)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
