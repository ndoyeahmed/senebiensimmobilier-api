package main.java.com.senebien.dao;

import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.models.Client;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ClientDao implements IClientDao {

    private Session session = HibernateInitializerConfig.getSession();

    @Override
    public boolean save(Client client) {
        return false;
    }

    @Override
    public List<Client> all() {
        try {
            return session.createQuery("select c from Client c", Client.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
