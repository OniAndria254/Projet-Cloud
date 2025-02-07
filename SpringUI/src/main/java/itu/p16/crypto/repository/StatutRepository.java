package itu.p16.crypto.repository;

import itu.p16.crypto.entity.Portefeuille;
import itu.p16.crypto.entity.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository  extends JpaRepository<Statut, Integer> {
}
