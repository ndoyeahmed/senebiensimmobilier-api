package main.java.com.senebien.dao.implementation;

import main.java.com.senebien.config.HibernateInitializerConfig;
import main.java.com.senebien.dao.Interfaces.IRoles;
import main.java.com.senebien.models.Role;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RolesImp implements IRoles {

    private Session session = HibernateInitializerConfig.getSession();

    @Override
    public List<Role> all() {
        List<Role> roles;
        try {
            roles =  session.createQuery("select r from Role r where r.archiver = false  and r.status=true ", Role.class).getResultList();
            return roles;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean save(Role role) {
        try {
            session.save(role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
