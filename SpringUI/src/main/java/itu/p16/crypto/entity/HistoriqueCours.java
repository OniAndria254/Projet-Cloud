package itu.p16.crypto.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "historique_cours")
public class HistoriqueCours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique_cours")
    private Integer idHistoriqueCours;

    @Column(name = "prix", nullable = false, precision = 15, scale = 2)
    private BigDecimal prix;

    @Column(name = "date_enregistrement", nullable = false)
    private LocalDate dateEnregistrement;

    @Column(name = "id_cryptomonnaie", nullable = false)
    private Integer idCryptomonnaie;

 
    public Integer getIdHistoriqueCours() {
        return idHistoriqueCours;
    }

    public void setIdHistoriqueCours(Integer idHistoriqueCours) {
        this.idHistoriqueCours = idHistoriqueCours;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public LocalDate getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(LocalDate dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Integer getIdCryptomonnaie() {
        return idCryptomonnaie;
    }

    public void setIdCryptomonnaie(Integer idCryptomonnaie) {
        this.idCryptomonnaie = idCryptomonnaie;
    }
}
