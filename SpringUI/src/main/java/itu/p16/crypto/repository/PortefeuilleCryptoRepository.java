package itu.p16.crypto.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import itu.p16.crypto.entity.PortefeuilleCrypto;

@Repository
public interface PortefeuilleCryptoRepository extends JpaRepository<PortefeuilleCrypto, Integer> {

    // @Modifying
    @Transactional
    @Query(value = "SELECT quantite FROM portefeuille_crypto WHERE id_utilisateur = :idUtilisateur AND id_cryptomonnaie = :idCryptomonnaie", nativeQuery = true)
    BigDecimal findQuantiteByUtilisateurAndCryptomonnaie(@Param("idUtilisateur") Integer idUtilisateur, @Param("idCryptomonnaie") Integer idCryptomonnaie);

    @Modifying
    @Transactional
    @Query(value = "UPDATE portefeuille_crypto SET quantite = quantite + :quantite WHERE id_utilisateur = :idUtilisateur AND id_cryptomonnaie = :idCryptomonnaie", nativeQuery = true)
    void updateQuantiteForBuy(@Param("idUtilisateur") Integer idUtilisateur, @Param("idCryptomonnaie") Integer idCryptomonnaie, @Param("quantite") BigDecimal quantite);

    @Modifying
    @Transactional
    @Query(value = "UPDATE portefeuille_crypto SET quantite = quantite - :quantite WHERE id_utilisateur = :idUtilisateur AND id_cryptomonnaie = :idCryptomonnaie", nativeQuery = true)
    void updateQuantiteForSell(@Param("idUtilisateur") Integer idUtilisateur, @Param("idCryptomonnaie") Integer idCryptomonnaie, @Param("quantite") BigDecimal quantite);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO portefeuille_crypto (id_utilisateur, quantite, id_cryptomonnaie) VALUES (:idUtilisateur, :quantite, :idCryptomonnaie)", nativeQuery = true)
    void insertIntoPortefeuilleCrypto(@Param("idUtilisateur") Integer idUtilisateur, @Param("quantite") BigDecimal quantite, @Param("idCryptomonnaie") Integer idCryptomonnaie);
}
