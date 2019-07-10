package main.java.com.senebien.dao;

import main.java.com.senebien.models.Profil;

import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 */
//@Local
public interface IProfilDao {
    boolean create(Profil profil);

    boolean update(Profil profil);

    List<Profil> all();

    List<Profil> allByStatusProfil(Boolean status);

    List<Profil> allByArchivedProfil(Boolean archive);

    Profil getOneById(Long id);

    Profil getProfilByLibelle(String libelle);
}
