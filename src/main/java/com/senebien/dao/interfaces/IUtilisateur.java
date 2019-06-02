package main.java.com.senebien.dao.interfaces;

import main.java.com.senebien.models.Profil;
import main.java.com.senebien.models.Utilisateur;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 01/06/2019
 * @version 1.0.0
 *
 * Interface containing all user prototype method with the database
 */
@Local
public interface IUtilisateur {
    /**
     * Function prototype that create a user in the database
     * @param user user that will be create
     * @return return true if all ok and false if there is an exception
     */
    boolean create(Utilisateur user);

    /**
     * Function prototype for updating a user
     * @param user user that will be update
     * @return return true if all ok and false if there is an exception
     */
    boolean update(Utilisateur user);

    /**
     * Function prototype to get all users in the database
     * @return return a list of all users
     */
    List<Utilisateur> all();

    /**
     * Function prototype to get all users that there account is activated
     * @return return a list of users
     */
    List<Utilisateur> allActivatedUser();

    /**
     * Function prototype to get all users that there account is disabled
     * @return return a list of users
     */
    List<Utilisateur> allDesactivatedUser();

    /**
     * Function prototype to get the list of users that are archived
     * @return return a list of users
     */
    List<Utilisateur> allArchivedUser();

    /**
     * Function prototype to get the list of users that are archived
     * @return return a list of users
     */
    List<Utilisateur> allUnarchivedUser();

    /**
     * Function prototype to get a user by his id
     * @param id the required parameter to get a user
     * @return return a user
     */
    Utilisateur getUserById(Long id);

    /**
     * Function prototype to verified if a user is registered on the database
     * and if the user is allowed to connect to the application
     * @param login a required parameter (user login)
     * @param password a required parameter (user password)
     * @param profil a required parameter (user profile)
     * @return return true if the user is registered and allowed and false if not
     */
    boolean getUserByUsernameAndPasswordAndProfile(String login, String password, Profil profil);

}
