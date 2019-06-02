package main.java.com.senebien.dao.interfaces;

import main.java.com.senebien.models.Profil;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 */
@Local
public interface IProfil {

    boolean create(Profil profil);

    boolean update(Profil profil);

    List<Profil> all();

    List<Profil> allActivatedProfil();

    List<Profil> allDisabledProfil();

    List<Profil> allArchivedProfil();

    List<Profil> allNoneArchivedProfil();

    Profil getOneById(Long id);

    Profil getProfilByLibelle(String libelle);
}
