package itu.p16.crypto.entity;

import itu.p16.crypto.firebase.listener.PortefeuilleCryptoListener;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@EntityListeners(PortefeuilleCryptoListener.class)
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
    @Basic
    @Column(name = "id_cryptomonnaie", nullable = false)
    private Integer idCryptomonnaie;

    @Column(name = "is_sync_from_firestore", nullable = false)
    private boolean isSyncFromFirestore = false;

    public boolean isSyncFromFirestore() {
        return isSyncFromFirestore;
    }

    public void setSyncFromFirestore(boolean syncFromFirestore) {
        isSyncFromFirestore = syncFromFirestore;
    }

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

    public Integer getIdCryptomonnaie() {
        return idCryptomonnaie;
    }

    public void setIdCryptomonnaie(Integer idCryptomonnaie) {
        this.idCryptomonnaie = idCryptomonnaie;
    }
}
