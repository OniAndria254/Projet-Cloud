package itu.p16.crypto.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
public class Cryptomonnaie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cryptomonnaie", nullable = false)
    private Integer idCryptomonnaie;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @Basic
    @Column(name = "symbole", nullable = true, length = 50)
    private String symbole;
    @Basic
    @Column(name = "date_creation", nullable = false)
    private Date dateCreation;
    @OneToMany(mappedBy = "cryptomonnaieByIdCryptomonnaie")
    private Collection<HistoriqueCours> historiqueCoursByIdCryptomonnaie;
    @OneToMany(mappedBy = "cryptomonnaieByIdCryptomonnaie")
    private Collection<PortefeuilleCrypto> portefeuilleCryptosByIdCryptomonnaie;
    @OneToMany(mappedBy = "cryptomonnaieByIdCryptomonnaie")
    private Collection<TransactionCrypto> transactionCryptosByIdCryptomonnaie;

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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cryptomonnaie that = (Cryptomonnaie) o;

        if (idCryptomonnaie != null ? !idCryptomonnaie.equals(that.idCryptomonnaie) : that.idCryptomonnaie != null)
            return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (symbole != null ? !symbole.equals(that.symbole) : that.symbole != null) return false;
        if (dateCreation != null ? !dateCreation.equals(that.dateCreation) : that.dateCreation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCryptomonnaie != null ? idCryptomonnaie.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (symbole != null ? symbole.hashCode() : 0);
        result = 31 * result + (dateCreation != null ? dateCreation.hashCode() : 0);
        return result;
    }

    public Collection<HistoriqueCours> getHistoriqueCoursByIdCryptomonnaie() {
        return historiqueCoursByIdCryptomonnaie;
    }

    public void setHistoriqueCoursByIdCryptomonnaie(Collection<HistoriqueCours> historiqueCoursByIdCryptomonnaie) {
        this.historiqueCoursByIdCryptomonnaie = historiqueCoursByIdCryptomonnaie;
    }

    public Collection<PortefeuilleCrypto> getPortefeuilleCryptosByIdCryptomonnaie() {
        return portefeuilleCryptosByIdCryptomonnaie;
    }

    public void setPortefeuilleCryptosByIdCryptomonnaie(Collection<PortefeuilleCrypto> portefeuilleCryptosByIdCryptomonnaie) {
        this.portefeuilleCryptosByIdCryptomonnaie = portefeuilleCryptosByIdCryptomonnaie;
    }

    public Collection<TransactionCrypto> getTransactionCryptosByIdCryptomonnaie() {
        return transactionCryptosByIdCryptomonnaie;
    }

    public void setTransactionCryptosByIdCryptomonnaie(Collection<TransactionCrypto> transactionCryptosByIdCryptomonnaie) {
        this.transactionCryptosByIdCryptomonnaie = transactionCryptosByIdCryptomonnaie;
    }
}
