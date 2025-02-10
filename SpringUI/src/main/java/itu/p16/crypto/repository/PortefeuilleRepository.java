package itu.p16.crypto.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import itu.p16.crypto.entity.Portefeuille;

@Repository
public interface PortefeuilleRepository extends JpaRepository<Portefeuille, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE portefeuille SET solde = solde - :montant WHERE id_utilisateur = :idUtilisateur", nativeQuery = true)
    void updateSoldeForBuy(@Param("idUtilisateur") Integer idUtilisateur, @Param("montant") BigDecimal montant);

    // Met à jour le solde en ajoutant un montant (pour une vente)
    @Modifying
    @Transactional
    @Query(value = "UPDATE portefeuille SET solde = solde + :montant WHERE id_utilisateur = :idUtilisateur", nativeQuery = true)
    void updateSoldeForSell(@Param("idUtilisateur") Integer idUtilisateur, @Param("montant") BigDecimal montant);

    @Transactional
    @Query(value = "SELECT * FROM portefeuille WHERE id_utilisateur = :idUtilisateur", nativeQuery = true)
    List<Portefeuille> findByIdUtilisateur(@Param("idUtilisateur") Integer idUtilisateur);

    
        
    @Transactional
    @Query(value = "SELECT solde FROM portefeuille WHERE id_utilisateur = :idUtilisateur", nativeQuery = true)
    BigDecimal findSoldeByUtilisateur(@Param("idUtilisateur") Integer idUtilisateur);
}
