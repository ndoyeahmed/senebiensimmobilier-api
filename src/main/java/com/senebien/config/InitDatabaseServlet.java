package main.java.com.senebien.config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InitDatabaseServlet", urlPatterns = "/", loadOnStartup = 1)
public class InitDatabaseServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        HibernateInitializerConfig config = new HibernateInitializerConfig();
        try {
            config.initDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
