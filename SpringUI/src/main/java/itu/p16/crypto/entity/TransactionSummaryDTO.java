package itu.p16.crypto.entity;

public interface TransactionSummaryDTO {
    Integer getIdUtilisateur();
    Integer getNombreAchats();
    Integer getNombreVentes();
    BigDecimal getValeurPorteFeuille();
}
