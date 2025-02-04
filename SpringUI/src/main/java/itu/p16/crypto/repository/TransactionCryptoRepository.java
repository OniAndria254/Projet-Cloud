package itu.p16.crypto.repository;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import itu.p16.crypto.entity.TransactionCrypto;

@Repository
public interface TransactionCryptoRepository extends JpaRepository<TransactionCrypto, Integer> {

    @Transactional
    @Query(value = "SELECT quantite FROM portefeuille_crypto WHERE id_utilisateur = :idUtilisateur AND id_cryptomonnaie = :idCryptomonnaie", nativeQuery = true)
    BigDecimal findQuantiteByUtilisateurAndCryptomonnaie(@Param("idUtilisateur") Integer idUtilisateur, @Param("idCryptomonnaie") Integer idCryptomonnaie);


    @Modifying
    @Transactional
    @Query(value = "UPDATE portefeuille_crypto SET quantite = quantite - :quantite WHERE id_utilisateur = :idUtilisateur AND id_cryptomonnaie = :idCryptomonnaie", nativeQuery = true)
    void updatePortefeuilleAfterVente(@Param("idUtilisateur") Integer idUtilisateur, @Param("idCryptomonnaie") Integer idCryptomonnaie, @Param("quantite") BigDecimal quantite);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transaction_crypto (id_utilisateur, quantite, prix_unitaire, montant_total, date_transaction, id_type_transaction, id_cryptomonnaie) " +
    "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
void insertTransactionCrypto(Integer idUtilisateur,
                    BigDecimal quantite,
                    BigDecimal prixUnitaire,
                    BigDecimal montantTotal,
                    Date dateTransaction,
                    Integer idTypeTransaction,
                    Integer idCryptomonnaie);
}