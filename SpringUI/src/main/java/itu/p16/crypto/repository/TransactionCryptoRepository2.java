package itu.p16.crypto.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import itu.p16.crypto.entity.TransactionCrypto;

@Repository
public interface TransactionCryptoRepository2 extends JpaRepository<TransactionCrypto, Long> {

    @Query(value = "SELECT MIN(quantite) AS min_quantite, MAX(quantite) AS max_quantite, AVG(quantite) AS moyenne_quantite, " +
                   "PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY quantite) AS premier_quartile, STDDEV(quantite) AS ecart_type " +
                   "FROM transaction_crypto WHERE Id_cryptomonnaie = :idCrypto AND date_transaction BETWEEN :dateMin AND :dateMax", nativeQuery = true)
    Map<String, Object> analyseTransactions(@Param("idCrypto") Long idCrypto, @Param("dateMin") LocalDateTime dateMin, @Param("dateMax") LocalDateTime dateMax);

    @Query(value = "SELECT SUM(commission_achat) AS somme_commission_achat, AVG(commission_achat) AS moyenne_commission_achat, " +
                   "SUM(commission_vente) AS somme_commission_vente, AVG(commission_vente) AS moyenne_commission_vente " +
                   "FROM commission WHERE Id_cryptomonnaie = :idCrypto AND date_modification BETWEEN :dateMin AND :dateMax", nativeQuery = true)
    Map<String, Object> analyseCommissions(@Param("idCrypto") Long idCrypto, @Param("dateMin") LocalDateTime dateMin, @Param("dateMax") LocalDateTime dateMax);

    @Query(value = "SELECT MIN(quantite) AS min_quantite, MAX(quantite) AS max_quantite, AVG(quantite) AS moyenne_quantite, " +
               "PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY quantite) AS premier_quartile, STDDEV(quantite) AS ecart_type " +
               "FROM transaction_crypto WHERE date_transaction BETWEEN :dateMin AND :dateMax", nativeQuery = true)
    Map<String, Object> analyseAllTransactions(@Param("dateMin") LocalDateTime dateMin, @Param("dateMax") LocalDateTime dateMax);

}