package itu.p16.crypto.entity;

import itu.p16.crypto.firebase.listener.PortefeuilleListener;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@EntityListeners(PortefeuilleListener.class)
public class Portefeuille {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_portefeuille", nullable = false)
    private Integer idPortefeuille;
    @Basic
    @Column(name = "solde", nullable = false, precision = 2)
    private BigDecimal solde;
    @Basic
    @Column(name = "date_creation", nullable = false)
    private Date dateCreation;
    @Basic
    @Column(name = "id_utilisateur", nullable = false)
    private Integer idUtilisateur;

    @Column(name = "is_sync_from_firestore", nullable = false)
    private boolean isSyncFromFirestore = false;

    public boolean isSyncFromFirestore() {
        return isSyncFromFirestore;
    }

    public void setSyncFromFirestore(boolean syncFromFirestore) {
        isSyncFromFirestore = syncFromFirestore;
    }

    public Integer getIdPortefeuille() {
        return idPortefeuille;
    }

    public void setIdPortefeuille(Integer idPortefeuille) {
        this.idPortefeuille = idPortefeuille;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Portefeuille that = (Portefeuille) o;

        if (idPortefeuille != null ? !idPortefeuille.equals(that.idPortefeuille) : that.idPortefeuille != null)
            return false;
        if (solde != null ? !solde.equals(that.solde) : that.solde != null) return false;
        if (dateCreation != null ? !dateCreation.equals(that.dateCreation) : that.dateCreation != null) return false;
        if (idUtilisateur != null ? !idUtilisateur.equals(that.idUtilisateur) : that.idUtilisateur != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPortefeuille != null ? idPortefeuille.hashCode() : 0;
        result = 31 * result + (solde != null ? solde.hashCode() : 0);
        result = 31 * result + (dateCreation != null ? dateCreation.hashCode() : 0);
        result = 31 * result + (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return result;
    }
}
