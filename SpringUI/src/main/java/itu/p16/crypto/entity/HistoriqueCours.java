package itu.p16.crypto.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "historique_cours", schema = "public", catalog = "cloud")
public class HistoriqueCours {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_historique_cours", nullable = false)
    private Integer idHistoriqueCours;
    @Basic
    @Column(name = "prix", nullable = false, precision = 2)
    private BigDecimal prix;
    @Basic
    @Column(name = "date_enregistrement", nullable = false)
    private Date dateEnregistrement;
    @ManyToOne
    @JoinColumn(name = "id_cryptomonnaie", referencedColumnName = "id_cryptomonnaie", nullable = false)
    private Cryptomonnaie cryptomonnaieByIdCryptomonnaie;

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

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Cryptomonnaie getCryptomonnaieByIdCryptomonnaie() {
        return cryptomonnaieByIdCryptomonnaie;
    }

    public void setCryptomonnaieByIdCryptomonnaie(Cryptomonnaie cryptomonnaieByIdCryptomonnaie) {
        this.cryptomonnaieByIdCryptomonnaie = cryptomonnaieByIdCryptomonnaie;
    }
}
