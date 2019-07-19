package main.java.com.senebien.controller;


import main.java.com.senebien.models.Utilisateur;
import main.java.com.senebien.services.UtilisateurService;
import main.java.com.senebien.utils.JsonResponse;
import main.java.com.senebien.utils.UserLogin;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mouhamed NDOYE
 * @since 2019-06-01
 * @version 1.0.0
 * This is a user controller class that provide many services for users
 */
@Path("/user")
public class UtilisateurController extends BaseController {

    private static final String ERROR_CODE = "error";
    private static final String SUCCES_CODE = "success";
    private JsonResponse jsonResponse = new JsonResponse();
    private final UtilisateurService service = new UtilisateurService();


    @POST
    @Path("/add-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(String body) {
        Utilisateur utilisateur = jsonResponse.getGsonInstance().fromJson(body, Utilisateur.class);
        utilisateur.setUsername("med");
        utilisateur.setPassword("passer@1");
        utilisateur.setStatus(true);
        utilisateur.setArchive(false);
        utilisateur.setArchive(false);
        utilisateur.setStatus(true);
        utilisateur.setDate(Timestamp.valueOf(LocalDateTime.now()));
        utilisateur.setProfil(service.getProfilById(utilisateur.getProfil().getId()));
        if (service.addUser(utilisateur))
            return Response.status(HttpServletResponse.SC_OK).entity(Collections.singletonMap(SUCCES_CODE, true)).build();
        else
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(Collections.singletonMap(ERROR_CODE, false)).build();
    }

    @GET
    @Path("/all-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        try {
            List<Utilisateur> utilisateurs = service.allUser();
            return Response.status(HttpServletResponse.SC_OK).entity(utilisateurs).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new ArrayList<>()).build();
        }
    }

    @GET
    @Path("/all-activated-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActivatedUser() {
        List<Utilisateur> utilisateurs = service.allUserByStatus(true);
        return Response.status(HttpServletResponse.SC_OK).entity(utilisateurs).build();
    }

    @GET
    @Path("/all-disabled-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDisabledUser() {
        List<Utilisateur> utilisateurs = service.allUserByStatus(false);
        return Response.status(HttpServletResponse.SC_OK).entity(utilisateurs).build();
    }

    @GET
    @Path("/all-archived-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArchivedUser() {
        List<Utilisateur> utilisateurs = service.allUserByArchive(true);
        return Response.status(HttpServletResponse.SC_OK).entity(utilisateurs).build();
    }

    @GET
    @Path("/all-unarchived-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUnarchivedUser() {
        List<Utilisateur> utilisateurs = service.allUserByArchive(false);
        return Response.status(HttpServletResponse.SC_OK).entity(utilisateurs).build();
    }

    @POST
    @Path("/get-user-by-id")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(String body) {
        Utilisateur utilisateur = service.getUserById(Long.valueOf(body));
        if (utilisateur != null) {
            return Response.status(HttpServletResponse.SC_OK).entity(utilisateur).build();
        } else
            return Response.status(HttpServletResponse.SC_EXPECTATION_FAILED).entity(Collections.singletonMap(ERROR_CODE, null)).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserLogin userLogin) {
        Utilisateur utilisateur = service.login(userLogin);
        if (utilisateur != null) {
            return Response.status(HttpServletResponse.SC_OK).entity(Collections.singletonMap(SUCCES_CODE, utilisateur)).build();
        } else
            return Response.status(HttpServletResponse.SC_FORBIDDEN).entity(Collections.singletonMap(SUCCES_CODE, false)).build();
    }
}
