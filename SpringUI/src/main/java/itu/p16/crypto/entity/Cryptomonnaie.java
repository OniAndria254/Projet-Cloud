package itu.p16.crypto.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import itu.p16.crypto.firebase.listener.CryptomonnaieListener;
import jakarta.persistence.*;

@Entity
@EntityListeners(CryptomonnaieListener.class)
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
    
    @Column(name = "icon", length = 255)
    private String icon;
    
    @Transient
    private BigDecimal currentPrice;

    @Column(name = "is_sync_from_firestore", nullable = false)
    private boolean isSyncFromFirestore = false;

    public boolean isSyncFromFirestore() {
        return isSyncFromFirestore;
    }

    public void setSyncFromFirestore(boolean syncFromFirestore) {
        isSyncFromFirestore = syncFromFirestore;
    }
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
