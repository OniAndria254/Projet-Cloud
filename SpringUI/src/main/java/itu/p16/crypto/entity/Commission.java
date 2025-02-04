package itu.p16.crypto.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "commission")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_commission")
    private Long idCommission;

    @Column(name = "Id_cryptomonnaie", nullable = false)
    private Long idCryptomonnaie;

    @Column(name = "commission_achat", nullable = false, precision = 5, scale = 2)
    private BigDecimal commissionAchat;

    @Column(name = "commission_vente", nullable = false, precision = 5, scale = 2)
    private BigDecimal commissionVente;

    @Column(name = "date_modification", nullable = false)
    private LocalDateTime dateModification;

    // Getters et Setters
    public Long getIdCommission() {
        return idCommission;
    }

    public void setIdCommission(Long idCommission) {
        this.idCommission = idCommission;
    }

    public Long getIdCryptomonnaie() {
        return idCryptomonnaie;
    }

    public void setIdCryptomonnaie(Long idCryptomonnaie) {
        this.idCryptomonnaie = idCryptomonnaie;
    }

    public BigDecimal getCommissionAchat() {
        return commissionAchat;
    }

    public void setCommissionAchat(BigDecimal commissionAchat) {
        this.commissionAchat = commissionAchat;
    }

    public BigDecimal getCommissionVente() {
        return commissionVente;
    }

    public void setCommissionVente(BigDecimal commissionVente) {
        this.commissionVente = commissionVente;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
}