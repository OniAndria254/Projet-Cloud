package itu.p16.crypto.repository;

import itu.p16.crypto.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {
    Commission findByIdCryptomonnaie(Long idCryptomonnaie);
}