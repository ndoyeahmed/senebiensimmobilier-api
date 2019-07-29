package main.java.com.senebien.controller;

import main.java.com.senebien.dao.ClientDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clients")
public class ClientController extends BaseController {

    private final ClientDao clientDao = new ClientDao();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response allClient() {
        return Response.status(200).entity(clientDao.all()).build();
    }
}
