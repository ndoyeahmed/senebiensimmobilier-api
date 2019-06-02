package main.java.com.senebien.controller;


import main.java.com.senebien.dao.IProfilDao;
import main.java.com.senebien.models.Profil;
import main.java.com.senebien.models.Utilisateur;
import main.java.com.senebien.utils.JsonResponse;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 * This is a user controller class that provide many services
 */
@Path("/profil")
public class ProfilController {
    @EJB
    IProfilDao iProfilDao;

    private JsonResponse jsonResponse = new JsonResponse();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String all() {
        try {
            return jsonResponse.getGsonInstance().toJson(iProfilDao.all());
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            return jsonResponse.getGsonInstance().toJson(new ArrayList<Profil>());
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addProfil(String body) {
        try {
            Profil profil = jsonResponse.getGsonInstance().fromJson(body, Profil.class);
            boolean result = iProfilDao.create(profil);
            if (result)
                return jsonResponse.getGsonInstance().toJson(Collections.singletonMap("success", true));
            else return jsonResponse.getGsonInstance().toJson(Collections.singletonMap("error", HttpServletResponse.SC_EXPECTATION_FAILED));
        } catch (Exception e) {
            return jsonResponse.getGsonInstance().toJson(Collections.singletonMap("error", HttpServletResponse.SC_BAD_REQUEST));
        }
    }
}
