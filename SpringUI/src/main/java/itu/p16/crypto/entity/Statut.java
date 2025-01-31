package itu.p16.crypto.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Statut {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_statut", nullable = false)
    private Integer idStatut;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @OneToMany(mappedBy = "statutByIdStatut")
    private Collection<TransactionFonds> transactionFondsByIdStatut;

    public Integer getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Integer idStatut) {
        this.idStatut = idStatut;
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

        Statut statut = (Statut) o;

        if (idStatut != null ? !idStatut.equals(statut.idStatut) : statut.idStatut != null) return false;
        if (nom != null ? !nom.equals(statut.nom) : statut.nom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStatut != null ? idStatut.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }

    public Collection<TransactionFonds> getTransactionFondsByIdStatut() {
        return transactionFondsByIdStatut;
    }

    public void setTransactionFondsByIdStatut(Collection<TransactionFonds> transactionFondsByIdStatut) {
        this.transactionFondsByIdStatut = transactionFondsByIdStatut;
    }
}
