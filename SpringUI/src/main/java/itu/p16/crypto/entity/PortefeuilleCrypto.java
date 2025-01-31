package itu.p16.crypto.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "portefeuille_crypto", schema = "public", catalog = "cloud")
public class PortefeuilleCrypto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_portefeuille_crypto", nullable = false)
    private Integer idPortefeuilleCrypto;
    @Basic
    @Column(name = "id_utilisateur", nullable = false)
    private Integer idUtilisateur;
    @Basic
    @Column(name = "quantite", nullable = false, precision = 2)
    private BigDecimal quantite;
    @ManyToOne
    @JoinColumn(name = "id_cryptomonnaie", referencedColumnName = "id_cryptomonnaie", nullable = false)
    private Cryptomonnaie cryptomonnaieByIdCryptomonnaie;

    public Integer getIdPortefeuilleCrypto() {
        return idPortefeuilleCrypto;
    }

    public void setIdPortefeuilleCrypto(Integer idPortefeuilleCrypto) {
        this.idPortefeuilleCrypto = idPortefeuilleCrypto;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public Cryptomonnaie getCryptomonnaieByIdCryptomonnaie() {
        return cryptomonnaieByIdCryptomonnaie;
    }

    public void setCryptomonnaieByIdCryptomonnaie(Cryptomonnaie cryptomonnaieByIdCryptomonnaie) {
        this.cryptomonnaieByIdCryptomonnaie = cryptomonnaieByIdCryptomonnaie;
    }
}
