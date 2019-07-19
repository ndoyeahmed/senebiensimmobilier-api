package main.java.com.senebien.controller;


import main.java.com.senebien.models.Profil;
import main.java.com.senebien.services.UtilisateurService;
import main.java.com.senebien.utils.JsonResponse;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 * This is a user controller class that provide many services for profile
 */
@Path("/profils")
public class ProfilController {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private JsonResponse jsonResponse = new JsonResponse();

    private final UtilisateurService service = new UtilisateurService();

    /**
     * @return return all profile like json object in a string
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        try {
            return Response.status(HttpServletResponse.SC_OK).entity(service.allProfil()).build();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new ArrayList<>()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allActivatedProfil() {
        try {
            return Response.status(HttpServletResponse.SC_OK).entity(service.allByStatusProfil(true)).build();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new ArrayList<Profil>()).build();
        }
    }

    @GET
    @Path("/all-disabled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allDisabledProfil() {
        try {
            return Response.status(HttpServletResponse.SC_OK).entity(service.allByStatusProfil(false)).build();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new ArrayList<Profil>()).build();
        }
    }

    @GET
    @Path("/all-archived")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allArchivedProfil() {
        try {
            return Response.status(HttpServletResponse.SC_OK).entity(service.allByArchivedProfil(true)).build();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new ArrayList<Profil>()).build();
        }
    }

    @GET
    @Path("/all-none-archived")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allNoneArchivedProfil() {
        try {
            return Response.status(HttpServletResponse.SC_OK).entity(service.allByArchivedProfil(false)).build();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new ArrayList<Profil>()).build();
        }
    }
}
