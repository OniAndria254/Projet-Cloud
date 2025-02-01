
package itu.p16.crypto.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cryptomonnaie")
public class Cryptomonnaie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cryptomonnaie")
    private Integer idCryptomonnaie;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "symbole", length = 50)
    private String symbole;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    // Getters and Setters

    public Integer getIdCryptomonnaie() {
        return idCryptomonnaie;
    }

    public void setIdCryptomonnaie(Integer idCryptomonnaie) {
        this.idCryptomonnaie = idCryptomonnaie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}

