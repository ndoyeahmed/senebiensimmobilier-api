package main.java.com.senebien.dao;

import main.java.com.senebien.models.Client;

import java.util.List;

public interface IClientDao {
    boolean save(Client client);

    List<Client> all();
}
