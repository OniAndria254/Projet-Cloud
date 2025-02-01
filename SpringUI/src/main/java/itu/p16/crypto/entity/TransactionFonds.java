package itu.p16.crypto.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
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
    @ManyToOne
    @JoinColumn(name = "id_statut", referencedColumnName = "id_statut", nullable = false)
    private Statut statutByIdStatut;
    @ManyToOne
    @JoinColumn(name = "id_type_transaction", referencedColumnName = "id_type_transaction", nullable = false)
    private TypeTransaction typeTransactionByIdTypeTransaction;

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

    public Statut getStatutByIdStatut() {
        return statutByIdStatut;
    }

    public void setStatutByIdStatut(Statut statutByIdStatut) {
        this.statutByIdStatut = statutByIdStatut;
    }

    public TypeTransaction getTypeTransactionByIdTypeTransaction() {
        return typeTransactionByIdTypeTransaction;
    }

    public void setTypeTransactionByIdTypeTransaction(TypeTransaction typeTransactionByIdTypeTransaction) {
        this.typeTransactionByIdTypeTransaction = typeTransactionByIdTypeTransaction;
    }
}
