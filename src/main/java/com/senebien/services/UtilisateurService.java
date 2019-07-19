package main.java.com.senebien.services;

import main.java.com.senebien.dao.ProfilDao;
import main.java.com.senebien.dao.UtilisateurDao;
import main.java.com.senebien.models.Profil;
import main.java.com.senebien.models.Utilisateur;
import main.java.com.senebien.utils.UserLogin;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurService {

    private final UtilisateurDao utilisateurDao = new UtilisateurDao();

    private final ProfilDao profilDao = new ProfilDao();

    public Profil getProfilById(Long id) {
        try {
            return profilDao.getOneById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Profil> allProfil() {
        try {
            return profilDao.all();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Profil> allByStatusProfil(boolean status) {
        try {
            return profilDao.allByStatusProfil(status);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Profil> allByArchivedProfil(boolean archive) {
        try {
            return profilDao.allByArchivedProfil(archive);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean addUser(Utilisateur utilisateur) {
        try {
            utilisateurDao.create(utilisateur);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Utilisateur> allUser() {
        try {
            return utilisateurDao.all();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Utilisateur> allUserByStatus(boolean status) {
        try {
            return utilisateurDao.allUserByStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Utilisateur> allUserByArchive(boolean archive) {
        try {
            return utilisateurDao.allUserByArchive(archive);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Utilisateur getUserById(Long id) {
        try {
            return utilisateurDao.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Utilisateur login(UserLogin userLogin) {
        try {
            return utilisateurDao.getUserByUsernameAndPasswordAndProfile(userLogin.getLogin(), userLogin.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
