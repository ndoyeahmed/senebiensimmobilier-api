package main.java.com.senebien.config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "InitDatabaseServlet", urlPatterns = "/", loadOnStartup = 1)
public class InitDatabaseServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void init() throws ServletException {
        HibernateInitializerConfig config = new HibernateInitializerConfig();
        try {
            config.initDatabase();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TO DO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TO DO
    }
}
