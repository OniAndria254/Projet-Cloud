package itu.p16.crypto.entity;

import java.math.BigDecimal;

public interface TransactionSummaryDTO {
    Integer getIdUtilisateur();
    Integer getNombreAchats();
    Integer getNombreVentes();
    BigDecimal getValeurPorteFeuille();
}
