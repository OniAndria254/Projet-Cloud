package itu.p16.crypto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import itu.p16.crypto.firebase.listener.TransactionFondsListener;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@EntityListeners(TransactionFondsListener.class)
@Table(name = "transaction_fonds", schema = "public", catalog = "cloud")
public class TransactionFonds {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_transaction_fonds", nullable = false)
    private Integer idTransactionFonds;
    @Basic
    @Column(name = "id_utilisateur", nullable = false)
    private Integer idUtilisateur;
    @Basic
    @Column(name = "montant", nullable = false, precision = 2)
    private BigDecimal montant;
    @Basic
    @Column(name = "date_transaction", nullable = false)
    private Date dateTransaction;
    @Basic
    @Column(name = "token_validation", nullable = true, length = 50)
    private String tokenValidation;
    @Column(name = "id_statut")
    private Integer idStatut;
    @Column(name = "id_type_transaction")
    private Integer idTypeTransaction;

    @Column(name = "is_sync_from_firestore", nullable = false)
    private boolean isSyncFromFirestore = false;

    public boolean isSyncFromFirestore() {
        return isSyncFromFirestore;
    }

    public void setSyncFromFirestore(boolean syncFromFirestore) {
        isSyncFromFirestore = syncFromFirestore;
    }

    public Integer getIdTransactionFonds() {
        return idTransactionFonds;
    }

    public void setIdTransactionFonds(Integer idTransactionFonds) {
        this.idTransactionFonds = idTransactionFonds;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getTokenValidation() {
        return tokenValidation;
    }

    public void setTokenValidation(String tokenValidation) {
        this.tokenValidation = tokenValidation;
    }

    public Integer getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Integer idStatut) {
        this.idStatut = idStatut;
    }

    public Integer getIdTypeTransaction() {
        return idTypeTransaction;
    }

    public void setIdTypeTransaction(Integer idTypeTransaction) {
        this.idTypeTransaction = idTypeTransaction;
    }
}
