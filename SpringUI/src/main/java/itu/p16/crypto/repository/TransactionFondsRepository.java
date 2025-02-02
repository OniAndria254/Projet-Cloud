package itu.p16.crypto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import itu.p16.crypto.entity.TransactionFonds;

@Repository
public interface TransactionFondsRepository extends JpaRepository<TransactionFonds, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM transaction_fonds WHERE id_statut = :statutId", nativeQuery = true)
    List<TransactionFonds> findByStatutId(@Param("statutId") Integer statutId);
    
    @Transactional
    @Query(value = "SELECT * FROM transaction_fonds WHERE id_utilisateur = :userId", nativeQuery = true)
    List<TransactionFonds> findByUserId(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE transaction_fonds SET id_statut = :newStatut WHERE id_transaction_fonds = :transactionId", nativeQuery = true)
    void updateTransactionFondsStatus(@Param("transactionId") Integer transactionId, @Param("newStatut") Integer newStatut);
}
