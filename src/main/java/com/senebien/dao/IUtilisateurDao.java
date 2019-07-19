package main.java.com.senebien.dao;

import main.java.com.senebien.models.Utilisateur;

import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 01/06/2019
 * @version 1.0.0
 *
 * Interface containing all user prototype method that interact with the database
 */
public interface IUtilisateurDao {
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
     * Function prototype to get all users by status value
     * @return return a list of users
     */
    List<Utilisateur> allUserByStatus(boolean status);

    /**
     * Function prototype to get the list of users by archive value
     * @return return a list of users
     */
    List<Utilisateur> allUserByArchive(boolean archive);

    /**
     * Function prototype to get a user by his id
     * @param id the required parameter to get a user
     * @return return a user
     */
    Utilisateur getUserById(Long id);

    /**
     * Function prototype to get a user by his username
     *
     * @param username the required parameter to get a user
     * @return return a user
     */
    Utilisateur getUserByUsername(String username);

    /**
     * Function prototype to verified if a user is registered on the database
     * and if the user is allowed to connect to the application
     * @param login a required parameter (user login)
     * @param password a required parameter (user password)
     * @return return true if the user is registered and allowed and false if not
     */
    Utilisateur getUserByUsernameAndPasswordAndProfile(String login, String password);

}
