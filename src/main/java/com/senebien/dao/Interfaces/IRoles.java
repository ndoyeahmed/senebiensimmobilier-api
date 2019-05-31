package main.java.com.senebien.dao.Interfaces;

import main.java.com.senebien.models.Role;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IRoles {
    public List<Role> all();

    public boolean save(Role role);
}
