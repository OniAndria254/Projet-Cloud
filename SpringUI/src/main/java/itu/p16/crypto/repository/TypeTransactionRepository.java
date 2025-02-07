package itu.p16.crypto.repository;

import itu.p16.crypto.entity.Statut;
import itu.p16.crypto.entity.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTransactionRepository   extends JpaRepository<TypeTransaction, Integer> {
}
