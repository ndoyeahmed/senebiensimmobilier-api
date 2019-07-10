package main.java.com.senebien.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mouhamed NDOYE
 * @version 1.0.0
 * @since 2019-06-30
 */
@Entity
public class Bien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String photo;
    private String libelle;
    private Double montantLocationBailleur;
    private Double montantLocationClient;
    private Boolean disponible;
    @ManyToOne
    @JoinColumn(name = "typeBien", referencedColumnName = "id")
    private TypeBien typeBien;
    @ManyToOne
    @JoinColumn(name = "bailleur", referencedColumnName = "id")
    private Bailleur bailleur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getMontantLocationBailleur() {
        return montantLocationBailleur;
    }

    public void setMontantLocationBailleur(Double montantLocationBailleur) {
        this.montantLocationBailleur = montantLocationBailleur;
    }

    public Double getMontantLocationClient() {
        return montantLocationClient;
    }

    public void setMontantLocationClient(Double montantLocationClient) {
        this.montantLocationClient = montantLocationClient;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public TypeBien getTypeBien() {
        return typeBien;
    }

    public void setTypeBien(TypeBien typeBien) {
        this.typeBien = typeBien;
    }

    public Bailleur getBailleur() {
        return bailleur;
    }

    public void setBailleur(Bailleur bailleur) {
        this.bailleur = bailleur;
    }
}
