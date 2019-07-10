package main.java.com.senebien.controller;


import main.java.com.senebien.dao.ProfilDao;
import main.java.com.senebien.models.Profil;
import main.java.com.senebien.utils.JsonResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 * This is a user controller class that provide many services for profile
 */
@Path("/profil")
public class ProfilController {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private ProfilDao iProfilDao = new ProfilDao();

    private JsonResponse jsonResponse = new JsonResponse();

    /**
     * @return return all profile like json object in a string
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String all() {
        try {
            return jsonResponse.getGsonInstance().toJson(iProfilDao.all());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return jsonResponse.getGsonInstance().toJson(new ArrayList<Profil>());
        }
    }

    @GET
    @Path("/all-activated")
    @Produces(MediaType.APPLICATION_JSON)
    public String allActivatedProfil() {
        try {
            return jsonResponse.getGsonInstance().toJson(iProfilDao.allByStatusProfil(true));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return jsonResponse.getGsonInstance().toJson(new ArrayList<Profil>());
        }
    }

    @GET
    @Path("/all-disabled")
    @Produces(MediaType.APPLICATION_JSON)
    public String allDisabledProfil() {
        try {
            return jsonResponse.getGsonInstance().toJson(iProfilDao.allByStatusProfil(false));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return jsonResponse.getGsonInstance().toJson(new ArrayList<Profil>());
        }
    }

    @GET
    @Path("/all-archived")
    @Produces(MediaType.APPLICATION_JSON)
    public String allArchivedProfil() {
        try {
            return jsonResponse.getGsonInstance().toJson(iProfilDao.allByArchivedProfil(true));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return jsonResponse.getGsonInstance().toJson(new ArrayList<Profil>());
        }
    }

    @GET
    @Path("/all-none-archived")
    @Produces(MediaType.APPLICATION_JSON)
    public String allNoneArchivedProfil() {
        try {
            return jsonResponse.getGsonInstance().toJson(iProfilDao.allByArchivedProfil(false));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return jsonResponse.getGsonInstance().toJson(new ArrayList<Profil>());
        }
    }
}
