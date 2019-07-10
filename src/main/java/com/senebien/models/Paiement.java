package main.java.com.senebien.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mouhamed NDOYE
 * @version 1.0.0
 * @since 2019-06-30
 */
@Entity
public class Paiement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @ManyToOne
    @JoinColumn(name = "typePaiement", referencedColumnName = "id")
    private TypePaiement typePaiement;

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

    public TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }
}
