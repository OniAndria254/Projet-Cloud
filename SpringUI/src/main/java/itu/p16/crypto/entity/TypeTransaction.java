package itu.p16.crypto.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "type_transaction", schema = "public", catalog = "cloud")
public class TypeTransaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_type_transaction", nullable = false)
    private Integer idTypeTransaction;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @OneToMany(mappedBy = "typeTransactionByIdTypeTransaction")
    private Collection<TransactionCrypto> transactionCryptosByIdTypeTransaction;
    @OneToMany(mappedBy = "typeTransactionByIdTypeTransaction")
    private Collection<TransactionFonds> transactionFondsByIdTypeTransaction;

    public Integer getIdTypeTransaction() {
        return idTypeTransaction;
    }

    public void setIdTypeTransaction(Integer idTypeTransaction) {
        this.idTypeTransaction = idTypeTransaction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeTransaction that = (TypeTransaction) o;

        if (idTypeTransaction != null ? !idTypeTransaction.equals(that.idTypeTransaction) : that.idTypeTransaction != null)
            return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTypeTransaction != null ? idTypeTransaction.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }

    public Collection<TransactionCrypto> getTransactionCryptosByIdTypeTransaction() {
        return transactionCryptosByIdTypeTransaction;
    }

    public void setTransactionCryptosByIdTypeTransaction(Collection<TransactionCrypto> transactionCryptosByIdTypeTransaction) {
        this.transactionCryptosByIdTypeTransaction = transactionCryptosByIdTypeTransaction;
    }

    public Collection<TransactionFonds> getTransactionFondsByIdTypeTransaction() {
        return transactionFondsByIdTypeTransaction;
    }

    public void setTransactionFondsByIdTypeTransaction(Collection<TransactionFonds> transactionFondsByIdTypeTransaction) {
        this.transactionFondsByIdTypeTransaction = transactionFondsByIdTypeTransaction;
    }
}
