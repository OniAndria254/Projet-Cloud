package itu.p16.crypto.repository;

import itu.p16.crypto.entity.TransactionCrypto;
import itu.p16.crypto.entity.TransactionSummaryDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionCryptoRepository3 extends CrudRepository<TransactionCrypto, Integer> {

    @Query(value = """
        WITH fonds_aggreg AS (
            SELECT 
                id_utilisateur,
                COALESCE(SUM(CASE 
                                WHEN Id_type_transaction = 1 AND Id_statut = 2 THEN montant 
                                WHEN Id_type_transaction = 2 AND Id_statut = 2 THEN -montant 
                                ELSE 0 
                             END), 0) AS valeur_fonds
        FROM transaction_fonds
        GROUP BY id_utilisateur
        )
        SELECT 
            t.id_utilisateur AS idUtilisateur,
            SUM(CASE WHEN t.Id_type_transaction = 3 THEN 1 ELSE 0 END) AS nombreAchats,
            SUM(CASE WHEN t.Id_type_transaction = 4 THEN 1 ELSE 0 END) AS nombreVentes,
            COALESCE(fa.valeur_fonds, 0) 
            + SUM(CASE 
                    WHEN t.Id_type_transaction = 4 THEN t.montant_total  
                    WHEN t.Id_type_transaction = 3 THEN -t.montant_total 
                    ELSE 0 
                 END) AS valeurPorteFeuille
        FROM transaction_crypto t
        LEFT JOIN fonds_aggreg fa ON t.id_utilisateur = fa.id_utilisateur
        GROUP BY t.id_utilisateur, fa.valeur_fonds
        """, nativeQuery = true)
    List<TransactionSummaryDTO> getTransactionSummary();

    @Query(value = """
        WITH fonds_aggreg AS (
            SELECT 
                id_utilisateur,
                COALESCE(SUM(CASE 
                                WHEN Id_type_transaction = 1 AND Id_statut = 2 THEN montant 
                                WHEN Id_type_transaction = 2 AND Id_statut = 2 THEN -montant 
                                ELSE 0 
                             END), 0) AS valeur_fonds
        FROM transaction_fonds
        GROUP BY id_utilisateur
        )
        SELECT 
            t.id_utilisateur AS idUtilisateur,
            SUM(CASE WHEN t.Id_type_transaction = 3 THEN 1 ELSE 0 END) AS nombreAchats,
            SUM(CASE WHEN t.Id_type_transaction = 4 THEN 1 ELSE 0 END) AS nombreVentes,
            COALESCE(fa.valeur_fonds, 0) 
            + SUM(CASE 
                    WHEN t.Id_type_transaction = 4 THEN t.montant_total  
                    WHEN t.Id_type_transaction = 3 THEN -t.montant_total 
                    ELSE 0 
                 END) AS valeurPorteFeuille
        FROM transaction_crypto t
        LEFT JOIN fonds_aggreg fa ON t.id_utilisateur = fa.id_utilisateur
        WHERE t.date_transaction <= :dateMax
        GROUP BY t.id_utilisateur, fa.valeur_fonds
        """, nativeQuery = true)
    List<TransactionSummaryDTO> getTransactionSummaryFilteredByDate(String dateMax);
}
