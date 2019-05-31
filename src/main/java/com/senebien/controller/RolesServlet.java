package main.java.com.senebien.controller;

import com.google.gson.Gson;
import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.dao.Interfaces.IRoles;
import main.java.com.senebien.models.Role;
import main.java.com.senebien.utils.JsonResponse;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RolesServlet", urlPatterns = "/roles")
public class RolesServlet extends HttpServlet {

    @EJB private IRoles iRoles;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            JsonResponse jsonResponse = new JsonResponse();
            String payload = buffer.toString();
            Role role = jsonResponse.getGsonInstance().fromJson(payload, Role.class);
            iRoles.save(role);
            jsonResponse.sendAsJson(response, role);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();

            if(pathInfo == null || pathInfo.equals("/")){
                JsonResponse jsonResponse = new JsonResponse();
                List<Role> roles = iRoles.all();
                jsonResponse.sendAsJson(response, roles);
                return;
            }
            String[] splits = pathInfo.split("/");

            if(splits.length != 2) {

                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
