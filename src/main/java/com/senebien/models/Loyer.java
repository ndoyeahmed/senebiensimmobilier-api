package main.java.com.senebien.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mouhamed NDOYE
 * @version 1.0.0
 * @since 2019-06-30
 */
@Entity
public class Loyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @ManyToOne
    @JoinColumn(name = "paiement", referencedColumnName = "id")
    private Paiement paiement;
    @ManyToOne
    @JoinColumn(name = "mois", referencedColumnName = "id")
    private Mois mois;
    @OneToOne(cascade = CascadeType.ALL)
    private Avance avance;
    @OneToOne(cascade = CascadeType.ALL)
    private Arriere arriere;

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

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Mois getMois() {
        return mois;
    }

    public void setMois(Mois mois) {
        this.mois = mois;
    }

    public Avance getAvance() {
        return avance;
    }

    public void setAvance(Avance avance) {
        this.avance = avance;
    }

    public Arriere getArriere() {
        return arriere;
    }

    public void setArriere(Arriere arriere) {
        this.arriere = arriere;
    }
}
