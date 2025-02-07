package itu.p16.crypto.entity;

import itu.p16.crypto.firebase.listener.TransactionCryptoListener;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@EntityListeners(TransactionCryptoListener.class)
@Table(name = "transaction_crypto", schema = "public", catalog = "cloud")
public class TransactionCrypto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_transaction_crypto", nullable = false)
    private Integer idTransactionCrypto;
    @Basic
    @Column(name = "id_utilisateur", nullable = false)
    private Integer idUtilisateur;
    @Basic
    @Column(name = "quantite", nullable = false, precision = 2)
    private BigDecimal quantite;
    @Basic
    @Column(name = "prix_unitaire", nullable = false, precision = 2)
    private BigDecimal prixUnitaire;
    @Basic
    @Column(name = "montant_total", nullable = false, precision = 2)
    private BigDecimal montantTotal;
    @Basic
    @Column(name = "date_transaction", nullable = false)
    private Date dateTransaction;
    @Column(name = "id_type_transaction")
    private Integer idTypeTransaction;
    @Column(name = "id_cryptomonnaie")
    private Integer idCryptomonnaie;

    @Column(name = "is_sync_from_firestore", nullable = false)
    private boolean isSyncFromFirestore = false;

    public boolean isSyncFromFirestore() {
        return isSyncFromFirestore;
    }

    public void setSyncFromFirestore(boolean syncFromFirestore) {
        isSyncFromFirestore = syncFromFirestore;
    }

    public Integer getIdTransactionCrypto() {
        return idTransactionCrypto;
    }

    public void setIdTransactionCrypto(Integer idTransactionCrypto) {
        this.idTransactionCrypto = idTransactionCrypto;
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

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }


    public Integer getIdTypeTransaction() {
        return idTypeTransaction;
    }

    public void setIdTypeTransaction(Integer idTypeTransaction) {
        this.idTypeTransaction = idTypeTransaction;
    }

    public Integer getIdCryptomonnaie() {
        return idCryptomonnaie;
    }

    public void setIdCryptomonnaie(Integer idCryptomonnaie) {
        this.idCryptomonnaie = idCryptomonnaie;
    }
}
