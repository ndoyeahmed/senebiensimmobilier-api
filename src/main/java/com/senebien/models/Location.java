package main.java.com.senebien.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mouhamed NDOYE
 * @version 1.0.0
 * @since 2019-06-30
 */
@Entity
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "bien", referencedColumnName = "id")
    private Bien bien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }
}
