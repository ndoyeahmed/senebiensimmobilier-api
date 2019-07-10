package main.java.com.senebien.controller;


import main.java.com.senebien.dao.ProfilDao;
import main.java.com.senebien.dao.UtilisateurDao;
import main.java.com.senebien.models.Utilisateur;
import main.java.com.senebien.utils.JsonResponse;
import main.java.com.senebien.utils.UserLogin;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 * This is a user controller class that provide many services for users
 */
@Path("/user")
public class UtilisateurController {

    private static final String ERROR_CODE = "error";
    private static final String SUCCES_CODE = "success";

    private final UtilisateurDao utilisateurDao = new UtilisateurDao();

    private final ProfilDao profilDao = new ProfilDao();

    private JsonResponse jsonResponse = new JsonResponse();

    @POST
    @Path("/add-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String body) {
        Utilisateur utilisateur = jsonResponse.getGsonInstance().fromJson(body, Utilisateur.class);
        utilisateur.setUsername("med");
        utilisateur.setPassword("passer@1");
        utilisateur.setStatus(true);
        utilisateur.setArchive(false);
        utilisateur.setArchive(false);
        utilisateur.setStatus(true);
        utilisateur.setDate(Timestamp.valueOf(LocalDateTime.now()));
        utilisateur.setProfil(profilDao.getOneById(utilisateur.getProfil().getId()));
        if (utilisateurDao.create(utilisateur))
            return jsonResponse.getGsonInstance().toJson(Collections.singletonMap(SUCCES_CODE, true));
        else
            return jsonResponse.getGsonInstance().toJson(Collections.singletonMap(ERROR_CODE, HttpServletResponse.SC_BAD_REQUEST));
    }

    @GET
    @Path("/all-user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUser() {
        List<Utilisateur> utilisateurs = utilisateurDao.all();
        return jsonResponse.getGsonInstance().toJson(utilisateurs);
    }

    @GET
    @Path("/all-activated-user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllActivatedUser() {
        List<Utilisateur> utilisateurs = utilisateurDao.allActivatedUser();
        return jsonResponse.getGsonInstance().toJson(utilisateurs);
    }

    @GET
    @Path("/all-disabled-user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDisabledUser() {
        List<Utilisateur> utilisateurs = utilisateurDao.allDesactivatedUser();
        return jsonResponse.getGsonInstance().toJson(utilisateurs);
    }

    @GET
    @Path("/all-archived-user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllArchivedUser() {
        List<Utilisateur> utilisateurs = utilisateurDao.allArchivedUser();
        return jsonResponse.getGsonInstance().toJson(utilisateurs);
    }

    @GET
    @Path("/all-unarchived-user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUnarchivedUser() {
        List<Utilisateur> utilisateurs = utilisateurDao.allUnarchivedUser();
        return jsonResponse.getGsonInstance().toJson(utilisateurs);
    }

    @POST
    @Path("/get-user-by-id")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(String body) {
        Utilisateur utilisateur = utilisateurDao.getUserById(Long.valueOf(body));
        if (utilisateur != null) {
            return jsonResponse.getGsonInstance().toJson(utilisateur);
        } else
            return jsonResponse.getGsonInstance().toJson(Collections.singletonMap(ERROR_CODE, HttpServletResponse.SC_EXPECTATION_FAILED));
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String body) {
        UserLogin userLogin = jsonResponse.getGsonInstance().fromJson(body, UserLogin.class);
        Utilisateur utilisateur = utilisateurDao.getUserByUsernameAndPasswordAndProfile(userLogin.getLogin(), userLogin.getPassword());
        if (utilisateur != null) {
            return jsonResponse.getGsonInstance().toJson(Collections.singletonMap(SUCCES_CODE, utilisateur));
        } else
            return jsonResponse.getGsonInstance().toJson(Collections.singletonMap(ERROR_CODE, HttpServletResponse.SC_FORBIDDEN));
    }
}
